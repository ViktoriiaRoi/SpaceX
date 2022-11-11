package com.viktoriiaroi.spacex.ui.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.core.model.News

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    private val newsList = mutableListOf<News>()

    fun setData(updatedList: List<News>) {
        val diffUtil = NewsDiffUtil(newsList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        newsList.clear()
        newsList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder.from(parent)

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }
}