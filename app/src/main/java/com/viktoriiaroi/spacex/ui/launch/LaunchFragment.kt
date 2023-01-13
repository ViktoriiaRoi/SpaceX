package com.viktoriiaroi.spacex.ui.launch

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.spacex.R
import com.viktoriiaroi.spacex.databinding.FragmentLaunchBinding
import com.viktoriiaroi.spacex.ui.common.BaseFragment
import com.viktoriiaroi.spacex.ui.launch.adapter.LaunchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LaunchFragment :
    BaseFragment<FragmentLaunchBinding, LaunchState, LaunchIntent, LaunchViewModel>() {
    override val viewModel: LaunchViewModel by viewModels()
    private val adapter = LaunchAdapter {
        val action = LaunchFragmentDirections.actionLaunchFragmentToBottomSheet(it)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycler(binding.launchesRecycler)
        setupMenu()
        binding.tryAgainBtn.setOnClickListener {
            viewModel.handleIntent(LaunchIntent.LoadAgain)
        }
        binding.toggleButtonGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                changeLaunchType(checkedId)
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.launch_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.search_item -> {
                        findNavController().navigate(LaunchFragmentDirections.actionLaunchFragmentToSearchFragment())
                        true
                    }
                    else -> false
                }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun changeLaunchType(buttonId: Int) {
        when (buttonId) {
            R.id.all_btn -> viewModel.handleIntent(LaunchIntent.ChangeType(LaunchType.ALL))
            R.id.past_btn -> viewModel.handleIntent(LaunchIntent.ChangeType(LaunchType.PAST))
            R.id.future_btn -> viewModel.handleIntent(LaunchIntent.ChangeType(LaunchType.FUTURE))
        }
    }

    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager?.itemCount ?: 0
                val lastVisible = layoutManager?.findLastVisibleItemPosition() ?: 0

                if ((totalItemCount >= 10) and (lastVisible > totalItemCount - 3)) {
                    if (viewModel.state.value !is LaunchState.LoadingNext) {
                        viewModel.handleIntent(LaunchIntent.LoadNextPage)
                    }
                }
            }
        })
    }

    override fun render(state: LaunchState) {
        binding.progressBar.isVisible = state is LaunchState.LoadingFirst
        binding.launchesRecycler.isVisible =
            (state is LaunchState.ResultLaunches) or (state is LaunchState.LoadingNext)
        binding.errorTv.isVisible = state is LaunchState.Error
        binding.tryAgainBtn.isVisible = state is LaunchState.Error

        when (state) {
            is LaunchState.ResultLaunches -> {
                adapter.setData(state.launches)
                if (viewModel.isTypeChanged) {
                    binding.launchesRecycler.scrollToPosition(0)
                }
            }
            is LaunchState.Error -> {
                binding.error = state.throwable
            }
            else -> {}
        }
    }

    override fun inflateBinding(layoutInflater: LayoutInflater) =
        FragmentLaunchBinding.inflate(layoutInflater)
}