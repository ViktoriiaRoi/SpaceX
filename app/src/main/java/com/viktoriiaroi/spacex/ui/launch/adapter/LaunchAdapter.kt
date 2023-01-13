package com.viktoriiaroi.spacex.ui.launch.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.core.model.Launch

class LaunchAdapter(private val onItemClick: (launch: Launch) -> Unit) : RecyclerView.Adapter<LaunchViewHolder>() {
    private val launchList = mutableListOf<Launch>()

    fun setData(updatedList: List<Launch>) {
        val diffUtil = LaunchDiffUtil(launchList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        launchList.clear()
        launchList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LaunchViewHolder.from(parent, onItemClick)

    override fun getItemCount() = launchList.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launchList[position])
    }
}