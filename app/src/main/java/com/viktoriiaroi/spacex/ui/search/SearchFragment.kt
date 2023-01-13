package com.viktoriiaroi.spacex.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.spacex.databinding.FragmentSearchBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.ui.launch.adapter.LaunchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchState, SearchIntent, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    private val adapter = LaunchAdapter {
        val action = SearchFragmentDirections.actionSearchFragmentToBottomSheet(it)
        findNavController().navigate(action)
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(binding.launchesRecycler)
        binding.tryAgainBtn.setOnClickListener {
            viewModel.handleIntent(SearchIntent.SearchAgain)
        }
        lifecycleScope.launch {
            listenTextChange(binding.searchView).collect {
                viewModel.handleIntent(SearchIntent.SearchByQuery(it))
            }
        }
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    @FlowPreview
    private fun listenTextChange(searchView: SearchView): Flow<String> = callbackFlow {
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleIntent(SearchIntent.Typing(newText?.isEmpty() ?: true))
                trySend(newText.orEmpty())
                return false
            }
        }
        searchView.setOnQueryTextListener(listener)
        awaitClose {
            searchView.setOnQueryTextListener(null)
        }
    }.debounce(1000)

    override fun render(state: SearchState) {
        binding.progressBar.isVisible = state is SearchState.Loading
        binding.launchesRecycler.isVisible = state is SearchState.ResultLaunches
        binding.errorTv.isVisible = state is SearchState.Error
        binding.tryAgainBtn.isVisible = state is SearchState.Error

        when (state) {
            is SearchState.Loading -> {}
            is SearchState.ResultLaunches -> {
                adapter.setData(state.launches)
                binding.launchesRecycler.scrollToPosition(0)
            }
            is SearchState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater) =
        FragmentSearchBinding.inflate(layoutInflater)
}