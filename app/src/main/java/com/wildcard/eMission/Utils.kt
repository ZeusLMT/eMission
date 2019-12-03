package com.wildcard.eMission

import android.graphics.*
import android.widget.TextView
import com.squareup.picasso.Transformation
import timber.log.Timber


class Utils {
    companion object {
        const val SHARE_PREFS = "EMISSION_SHARED_PREFS"
        const val PREF_DATE = "LAST_GENERATED_DATE"
        const val PREF_USER = "CURRENT_USER"
        const val PREF_THEME = "CURRENT_THEME"
        const val PREF_UNLOCKED_PACK = "UNLOCKED_CHALLENGE_PACKS"

        fun setGradientTextColor(textView: TextView, colorStart: Int, colorEnd: Int) {
            textView.setTextColor(colorEnd)
            val x1 = getTextSize(textView)
            Timber.d("$x1")
            val gradientShader =
                LinearGradient(0f, 0f, x1, 0f, colorStart, colorEnd, Shader.TileMode.MIRROR)
            textView.paint.shader = gradientShader
        }


        private fun getTextSize(textView: TextView): Float {
            return textView.paint.measureText(textView.text.toString())
        }

        class PicassoCircleTransformation : Transformation {
            override fun transform(source: Bitmap): Bitmap {
                val size = Math.min(source.width, source.height)
                val x = (source.width - size) / 2
                val y = (source.height - size) / 2
                val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
                if (squaredBitmap != source) {
                    source.recycle()
                }
                val bitmap = Bitmap.createBitmap(size, size, source.config)
                val canvas = Canvas(bitmap)
                val paint = Paint()
                val shader = BitmapShader(
                    squaredBitmap,
                    Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
                )
                paint.shader = shader
                paint.isAntiAlias = true
                val r = size / 2f
                canvas.drawCircle(r, r, r, paint)
                squaredBitmap.recycle()
                return bitmap
            }

            override fun key(): String {
                return "circle"
            }
        }
    }
}