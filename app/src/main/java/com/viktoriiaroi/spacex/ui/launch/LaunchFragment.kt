package com.viktoriiaroi.spacex.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.spacex.R
import com.viktoriiaroi.spacex.databinding.FragmentLaunchBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.ui.launch.adapter.LaunchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchFragment :
    BaseFragment<FragmentLaunchBinding, LaunchState, LaunchIntent, LaunchViewModel>() {
    override val viewModel: LaunchViewModel by viewModels()
    private val adapter = LaunchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler(binding.launchesRecycler)
        binding.tryAgainBtn.setOnClickListener {
            updateLaunches(binding.toggleButtonGroup.checkedButtonId)
        }
        binding.toggleButtonGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                updateLaunches(checkedId)
            }
        }
    }

    private fun updateLaunches(buttonId: Int) {
        when (buttonId) {
            R.id.all_btn -> viewModel.handleIntent(LaunchIntent.LoadAllLaunches)
            R.id.past_btn -> viewModel.handleIntent(LaunchIntent.LoadPastLaunches)
            R.id.future_btn -> viewModel.handleIntent(LaunchIntent.LoadFutureLaunches)
        }
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun render(state: LaunchState) {
        binding.progressBar.isVisible = state is LaunchState.Loading
        binding.launchesRecycler.isVisible = state is LaunchState.ResultLaunches
        binding.errorTv.isVisible = state is LaunchState.Error
        binding.tryAgainBtn.isVisible = state is LaunchState.Error

        when (state) {
            is LaunchState.Loading -> {}
            is LaunchState.ResultLaunches -> {
                adapter.setData(state.launches)
                binding.launchesRecycler.scrollToPosition(0)
            }
            is LaunchState.Error -> {
                binding.error = state.throwable
            }
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater) =
        FragmentLaunchBinding.inflate(layoutInflater)
}