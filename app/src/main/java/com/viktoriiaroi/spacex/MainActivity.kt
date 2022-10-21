package com.viktoriiaroi.spacex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SpaceX)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
