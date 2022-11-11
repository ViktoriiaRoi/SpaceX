package com.viktoriiaroi.spacex.ui.news.adapter

import androidx.recyclerview.widget.DiffUtil
import com.viktoriiaroi.core.model.News

class NewsDiffUtil(
    private val oldList: List<News>,
    private val newList: List<News>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}