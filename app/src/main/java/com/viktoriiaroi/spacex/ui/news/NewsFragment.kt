package com.viktoriiaroi.spacex.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.spacex.databinding.FragmentNewsBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.ui.news.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsFragment :
    BaseFragment<FragmentNewsBinding, NewsState, NewsIntent, NewsViewModel>() {
    override val viewModel: NewsViewModel by viewModels()
    private val adapter = NewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tryAgainBtn.setOnClickListener { viewModel.handleIntent(NewsIntent.LoadNews) }
        setupRecycler(binding.newsRecycler)
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun render(state: NewsState) {
        binding.progressBar.isVisible = state is NewsState.Loading
        binding.newsRecycler.isVisible = state is NewsState.ResultNews
        binding.errorTv.isVisible = state is NewsState.Error
        binding.tryAgainBtn.isVisible = state is NewsState.Error

        when (state) {
            is NewsState.Loading -> {}
            is NewsState.ResultNews -> {
                adapter.setData(state.news)
            }
            is NewsState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater) =
        FragmentNewsBinding.inflate(layoutInflater)
}