package com.wildcard.eMission

import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView
import timber.log.Timber

class Utils {
    companion object {
        val PREF_DATE = "LAST_GENERATED_DATE"
        val PREF_USER = "CURRENT_USER"

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
    }
}