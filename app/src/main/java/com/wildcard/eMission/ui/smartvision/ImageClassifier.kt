package com.wildcard.eMission.ui.smartvision

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.Size
import org.tensorflow.lite.DataType
import org.tensorflow.lite.InterpreterApi
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.TensorProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import timber.log.Timber
import java.io.Closeable
import java.util.*
import kotlin.math.min

class ImageClassifier (context: Context, private val maxResult: Int) : Closeable {
    private var tfInputBuffer = TensorImage(DataType.UINT8)
    private var tfImageProcessor: ImageProcessor? = null
    private val preprocessNormalizeOp = NormalizeOp(127.0f, 128.0f)
    private val postprocessNormalizeOp = NormalizeOp(0.0f, 1.0f)
    private val labels by lazy { FileUtil.loadLabels(context, LABELS_PATH) }

    // Processor to apply post processing of the output probability
    private val probabilityProcessor = TensorProcessor.Builder().add(postprocessNormalizeOp).build()

    // Use TFLite in Play Services runtime by setting the option to FROM_SYSTEM_ONLY
    private val interpreterInitializer = lazy {
        val interpreterOption = InterpreterApi.Options().setRuntime(InterpreterApi.Options.TfLiteRuntime.FROM_SYSTEM_ONLY)
        InterpreterApi.create(FileUtil.loadMappedFile(context, MODEL_PATH), interpreterOption)
    }
    // Only use interpreter after initialization finished in CameraActivity
    private val interpreter: InterpreterApi by interpreterInitializer
    private val tfInputSize by lazy {
        val inputIndex = 0
        val inputShape = interpreter.getInputTensor(inputIndex).shape()
        Size(inputShape[2], inputShape[1]) // Order of axis is: {1, height, width, 3}
    }

    // Output probability TensorBuffer
    private val outputProbabilityBuffer: TensorBuffer by lazy {
        val probabilityTensorIndex = 0
        val probabilityShape =
            interpreter.getOutputTensor(probabilityTensorIndex).shape() // {1, NUM_CLASSES}
        val probabilityDataType = interpreter.getOutputTensor(probabilityTensorIndex).dataType()
        TensorBuffer.createFixedSize(probabilityShape, probabilityDataType)
    }

    /** Classifies the input bitmapBuffer. */
    fun classify(bitmapBuffer: Bitmap, imageRotationDegrees: Int): List<Recognition> {
        // Loads the input bitmapBuffer
        tfInputBuffer = loadImage(bitmapBuffer, imageRotationDegrees)
        Timber.d("tensorSize: ${tfInputBuffer.width} x ${tfInputBuffer.height}")

        // Runs the inference call
        interpreter.run(tfInputBuffer.buffer, outputProbabilityBuffer.buffer.rewind())

        // Gets the map of label and probability
        val labeledProbability =
            TensorLabel(labels, probabilityProcessor.process(outputProbabilityBuffer)).mapWithFloatValue

        return getTopNProbability(labeledProbability)
    }

    /** Loads input image, and applies preprocessing. */
    private fun loadImage(bitmapBuffer: Bitmap, imageRotationDegrees: Int): TensorImage {
        // Initializes preprocessor if null
        return (tfImageProcessor
            ?: run {
                val cropSize = minOf(bitmapBuffer.width, bitmapBuffer.height)
                ImageProcessor.Builder()
                    .add(ResizeWithCropOrPadOp(cropSize, cropSize))
                    .add(
                        ResizeOp(
                            tfInputSize.height,
                            tfInputSize.width,
                            ResizeOp.ResizeMethod.NEAREST_NEIGHBOR
                        )
                    )
                    .add(Rot90Op(-imageRotationDegrees / 90))
                    .add(preprocessNormalizeOp)
                    .build()
                    .also {
                        tfImageProcessor = it
                        Timber.d("tfImageProcessor initialized successfully. imageSize: $cropSize")
                    }
            })
            .process(tfInputBuffer.apply { load(bitmapBuffer) })
    }

    /** Gets the top n results. */
    private fun getTopNProbability(labelProb: Map<String, Float>): List<Recognition> {
        // Sort the recognition by confidence from high to low.
        val pq: PriorityQueue<Recognition> =
            PriorityQueue(maxResult, compareByDescending<Recognition> { it.accuracy })
        pq += labelProb.map { (label, prob) -> Recognition(label, label, prob) }
        return List(min(maxResult, pq.size)) { pq.poll()!! }
    }

    override fun close() {
        if (interpreterInitializer.isInitialized()) {
            interpreter.close()
        }
    }

    companion object {
        private const val MODEL_PATH = "mobilenet_v3_large_minimalistic_224_1.0_float.tflite"
        private const val LABELS_PATH = "mobilenet_labels.txt"
    }
}