package com.viktoriiaroi.spacex.ui.launch.adapter

import androidx.recyclerview.widget.DiffUtil
import com.viktoriiaroi.core.model.Launch

class LaunchDiffUtil(
    private val oldList: List<Launch>,
    private val newList: List<Launch>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}