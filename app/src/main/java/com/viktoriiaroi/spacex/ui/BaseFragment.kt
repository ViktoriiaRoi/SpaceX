package com.viktoriiaroi.spacex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment() {

    companion object {
        private const val LIFECYCLE_TAG = "FragmentLifecycle"
    }

    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    private val className = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LIFECYCLE_TAG, "$className: onCreate() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(LIFECYCLE_TAG, "$className: onCreateView() called")
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        Log.d(LIFECYCLE_TAG, "$className: onStart() called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(LIFECYCLE_TAG, "$className: onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(LIFECYCLE_TAG, "$className: onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LIFECYCLE_TAG, "$className: onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LIFECYCLE_TAG, "$className: onDestroy() called")
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LIFECYCLE_TAG, "$className: onDestroyView() called")
        _binding = null
    }
}