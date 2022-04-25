package com.wildcard.eMission.ui.smartvision

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.android.gms.tflite.java.TfLite
import com.wildcard.eMission.databinding.ActivitySmartVisionBinding
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.log

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SmartVisionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySmartVisionBinding
    private lateinit var fullscreenContentLayout: ConstraintLayout
    private val hideHandler = Handler()

    // Camera activity related variables
    private lateinit var cameraExecutor: ExecutorService
    private var pauseFeed = false
    private var imageRotationDegrees: Int = 0

    // Inference related variables
    private lateinit var bitmapBuffer: Bitmap
    private val initializeTask: Task<Void> by lazy { TfLite.initialize(this) }
    private var classifier: ImageClassifier? = null
//    private var classifier: ImageClassificationHelper? = null

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        fullscreenContentLayout.windowInsetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
    }
    private var isFullscreen: Boolean = false

    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS)
            }
            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySmartVisionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFullscreen = true

        Log.d("CameraX","OnCreate")

        // Initialize TFLite asynchronously
        initializeTask
            .addOnSuccessListener {
                Log.d("CameraX:", "TFLite in Play Services initialized successfully.")
                classifier = ImageClassifier(this, NUM_OF_RESULTS)
//                classifier = ImageClassificationHelper(this, NUM_OF_RESULTS)
            }
            .addOnFailureListener { e -> Log.e("CameraX", "TFLite in Play Services failed to initialize.", e) }


        // Request camera permissions
        if (allPermissionsGranted()) {
            Log.d("CameraX","all permission granted")
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContentLayout = binding.fullscreenContentLayout
        fullscreenContentLayout.setOnClickListener { toggle() }

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        binding.dummyButton.setOnTouchListener(delayHideTouchListener)
        binding.dummyButton.setOnClickListener {
            finish()
        }

        // Set up the listeners for take photo and video capture buttons
        binding.imageCaptureButton.setOnClickListener { pauseCameraFeed() }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        classifier?.close()
    }

    private fun toggle() {
        if (isFullscreen) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        isFullscreen = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        if (Build.VERSION.SDK_INT >= 30) {
            fullscreenContentLayout.windowInsetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            fullscreenContentLayout.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }
        isFullscreen = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() = binding.viewFinder.post {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        Log.d("CameraX", "StartCamera")

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider = cameraProviderFuture.get()

            // Set up preview viewfinder
            val preview = Preview.Builder()
                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                .setTargetRotation(binding.viewFinder.display.rotation)
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            // Set up the image analysis use case which will process frames in real time
            val imageAnalysis =
                ImageAnalysis.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .setTargetRotation(binding.viewFinder.display.rotation)
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                    .build()

            var frameCounter = 0
            var lastFpsTimestamp = System.currentTimeMillis()

            imageAnalysis.setAnalyzer(cameraExecutor, ImageAnalysis.Analyzer { image ->
                Log.d("CameraX", "Image analyzing")
                if (!::bitmapBuffer.isInitialized) {
                    // The image rotation and RGB image buffer are initialized only once
                    // the analyzer has started running
                    imageRotationDegrees = image.imageInfo.rotationDegrees
                    bitmapBuffer =
                        Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
                }

                // Early exit: image analysis is in paused state, or TFLite is not initialized
                if (pauseFeed || classifier == null) {
                    image.close()
                    return@Analyzer
                }

                // Copy out RGB bits to our shared buffer
                image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }

                // Perform the image classification for the current frame
                val recognitions = classifier?.classify(bitmapBuffer, imageRotationDegrees)

                showResults(recognitions)

                // Compute the FPS of the entire pipeline
                val frameCount = 100
                if (++frameCounter % frameCount == 0) {
                    frameCounter = 0
                    val now = System.currentTimeMillis()
                    val delta = now - lastFpsTimestamp
                    val fps = 1000 * frameCount.toFloat() / delta
                    Timber.d("FPS: ${"%.02f".format(fps)}")
                    lastFpsTimestamp = now
                }
            })

            // Select back camera as a default
            val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageAnalysis
                )

            } catch (exc: Exception) {
                Timber.e("Use case binding failed")
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun pauseCameraFeed() {
        Log.d("CameraX","Pause")
        // Disable capture button while changing the state
        binding.imageCaptureButton.isEnabled = false
        if (pauseFeed) {
            // Resume camera feed if paused
            pauseFeed = false
            binding.imageFreezeFrame.visibility = View.GONE
        } else {
            // Pause inference and show captured frame, ensure the orientation of the image
            pauseFeed = true
            val matrix =
                Matrix().apply {
                    postRotate(imageRotationDegrees.toFloat())
                }
            val uprightImage =
                Bitmap.createBitmap(
                    bitmapBuffer,
                    0,
                    0,
                    bitmapBuffer.width,
                    bitmapBuffer.height,
                    matrix,
                    true
                )
            binding.imageFreezeFrame.setImageBitmap(uprightImage)
            binding.imageFreezeFrame.visibility = View.VISIBLE
        }

        // Re-enable camera controls
        binding.imageCaptureButton.isEnabled = true
    }

    private fun showResults(
        recognitions: List<Recognition>?,
    ) =
        binding.viewFinder.post {

            // Early exit: if recognition is null, or there are not enough recognition results.
            if (recognitions == null || recognitions.size < NUM_OF_RESULTS) {
                binding.textPredictions.visibility = View.GONE
                return@post
            }

            // Update the text and UI
            binding.textPredictions.text =
                recognitions.subList(0, NUM_OF_RESULTS).joinToString(separator = "\n") {
                    "${"%.2f".format(it.accuracy)} ${it.title}"
                }

            // Make sure all UI elements are visible
            binding.textPredictions.visibility = View.VISIBLE
        }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300

        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).toTypedArray()

        private const val NUM_OF_RESULTS = 3
    }
}