package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.model.NewsEntity

data class News(
    val id: Int,
    val title: String,
    val event_date: Int? = null,
    val details: String,
    val articleLink: String? = null,
) {

    companion object {
        fun fromEntity(src: NewsEntity) = News(
            id = src.id,
            title = src.title,
            event_date = src.event_date,
            details = src.details,
            articleLink = src.articleLink
        )
    }
}