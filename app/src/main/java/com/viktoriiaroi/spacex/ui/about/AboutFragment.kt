package com.viktoriiaroi.spacex.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.viktoriiaroi.spacex.databinding.FragmentAboutBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.utils.DesignUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment :
    BaseFragment<FragmentAboutBinding, AboutState, AboutIntent, AboutViewModel>() {
    override val viewModel: AboutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DesignUtils.gradientTextView(requireContext(), binding.logoTv)
        binding.tryAgainBtn.setOnClickListener { viewModel.handleIntent(AboutIntent.LoadCompany) }
        binding.ceoTv.hyperlinkStyle()
        binding.websiteTv.hyperlinkStyle()
    }

    override fun render(state: AboutState) {
        binding.progressBar.isVisible = state is AboutState.Loading
        binding.successLayout.isVisible = state is AboutState.ResultCompany
        binding.errorLayout.isVisible = state is AboutState.Error

        when (state) {
            is AboutState.Loading -> {}
            is AboutState.ResultCompany -> {
                binding.company = state.company
            }
            is AboutState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater) =
        FragmentAboutBinding.inflate(layoutInflater)

    private fun TextView.hyperlinkStyle() {
        text = DesignUtils.underlinedText(text.toString())
        DesignUtils.gradientTextView(context, this)
    }
}
