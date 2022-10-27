package com.viktoriiaroi.spacex.utils

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.viktoriiaroi.spacex.R

class DesignUtils {
    companion object {
        fun gradientTextView(context: Context, textView: TextView) {
            val width: Float = textView.paint.measureText(textView.text.toString())
            val textShader: Shader = LinearGradient(0f,
                0f,
                width,
                textView.textSize,
                intArrayOf(ContextCompat.getColor(context, R.color.gradient_start),
                    ContextCompat.getColor(context, R.color.gradient_end)),
                floatArrayOf(0f, 1f),
                Shader.TileMode.CLAMP)
            textView.paint.shader = textShader
        }

        fun underlinedText(text: String?): SpannableString {
            val spannableString = SpannableString(text)
            spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
            return spannableString
        }
    }
}