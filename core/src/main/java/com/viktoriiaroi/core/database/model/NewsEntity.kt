package com.viktoriiaroi.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viktoriiaroi.core.network.model.news.NewsDTO

@Entity(tableName = "news")
class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "event_date")
    val event_date: Long? = null,
    @ColumnInfo(name = "details")
    val details: String,
    @ColumnInfo(name = "article_link")
    val articleLink: String? = null,
) {
    companion object {
        fun fromDTO(src: NewsDTO) = NewsEntity(
            id = 0,
            title = src.title.orEmpty(),
            event_date = src.event_date_unix,
            details = src.details.orEmpty(),
            articleLink = src.links?.article
        )
    }
}