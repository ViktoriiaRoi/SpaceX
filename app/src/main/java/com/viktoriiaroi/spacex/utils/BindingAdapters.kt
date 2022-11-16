package com.viktoriiaroi.spacex.utils

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.viktoriiaroi.spacex.R

class BindingAdapters {
    companion object {
        @BindingAdapter("android:navigateToWeb")
        @JvmStatic
        fun navigateToWeb(view: View, hyperlink: String?) {
            view.setOnClickListener {
                hyperlink?.let {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    ContextCompat.startActivity(view.context, browserIntent, null)
                }
            }
        }

        @BindingAdapter("android:loadImage")
        @JvmStatic
        fun loadImage(imageView: ImageView, path: String?) {
            if (path == null) return
            imageView.load(path) {
                placeholder(R.drawable.default_launch)
                error(R.drawable.default_launch)
            }
        }

        @BindingAdapter("android:joinList")
        @JvmStatic
        fun joinList(textView: TextView, list: List<String>) {
            textView.text =
                if (list.isEmpty()) "None"
                else list.joinToString(separator = ", ")
        }
    }
}