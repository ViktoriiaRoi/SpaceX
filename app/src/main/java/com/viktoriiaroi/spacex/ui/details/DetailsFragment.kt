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

    private val viewModel: DetailsViewModel by viewModels()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.iconClose.setOnClickListener { dismiss() }
        binding.tryAgainBtn.setOnClickListener { loadDetails() }
        viewModel.state.observe(viewLifecycleOwner) { render(it) }
        loadDetails()
        return binding.root
    }

    private fun loadDetails() {
        viewModel.handleIntent(DetailsIntent.LoadDetails(args.launch.rocketId, args.launch.coreId))
    }

    private fun render(state: DetailsState) {
        binding.progressBar.isVisible = state is DetailsState.Loading
        binding.errorTv.isVisible = state is DetailsState.Error
        binding.tryAgainBtn.isVisible = state is DetailsState.Error
        binding.cardView.isVisible = state is DetailsState.ResultDetails

        when (state) {
            is DetailsState.Loading -> {}
            is DetailsState.ResultDetails -> {
                binding.launch = args.launch
                binding.rocket = state.rocket
                binding.core = state.core
                if (state.core.status == Status.ACTIVE) {
                    DesignUtils.gradientTextView(requireContext(), binding.statusTv)
                }
            }
            is DetailsState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}