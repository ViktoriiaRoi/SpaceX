package com.viktoriiaroi.core.database

import androidx.room.*
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.database.model.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    suspend fun getNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsList: List<NewsEntity>)

    @Query("DELETE FROM news")
    suspend fun deleteNews()
}