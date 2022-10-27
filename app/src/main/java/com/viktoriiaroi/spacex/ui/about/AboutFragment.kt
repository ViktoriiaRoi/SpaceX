package com.viktoriiaroi.spacex.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.viktoriiaroi.spacex.databinding.FragmentAboutBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.utils.DesignUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment :
    BaseFragment<FragmentAboutBinding, AboutState, AboutIntent, AboutViewModel>(FragmentAboutBinding::inflate) {
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
                binding.ceoTv.setOnClickListener { navigateToWeb(state.company.seoLink) }
                binding.websiteTv.setOnClickListener { navigateToWeb(state.company.websiteLink) }
            }
            is AboutState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    private fun TextView.hyperlinkStyle() {
        text = DesignUtils.underlinedText(text.toString())
        DesignUtils.gradientTextView(context, this)
    }

    private fun navigateToWeb(hyperlink: String?) {
        hyperlink?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(hyperlink))
            ContextCompat.startActivity(requireContext(), browserIntent, null)
        }
    }
}
