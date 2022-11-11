package com.viktoriiaroi.spacex.utils

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

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
    }
}