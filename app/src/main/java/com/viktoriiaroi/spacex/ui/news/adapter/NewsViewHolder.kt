package com.viktoriiaroi.spacex.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viktoriiaroi.core.model.News
import com.viktoriiaroi.spacex.databinding.ItemNewsBinding
import com.viktoriiaroi.spacex.utils.DateUtils

class NewsViewHolder(private val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(news: News) {
        binding.news = news
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): NewsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
            return NewsViewHolder(binding)
        }
    }
}
