package com.viktoriiaroi.spacex.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.viktoriiaroi.core.model.Status
import com.viktoriiaroi.spacex.databinding.FragmentDetailsBinding
import com.viktoriiaroi.spacex.utils.DesignUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.iconClose.setOnClickListener { dismiss() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}