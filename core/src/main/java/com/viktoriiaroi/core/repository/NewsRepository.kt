package com.viktoriiaroi.core.repository

import com.viktoriiaroi.core.database.NewsDao
import com.viktoriiaroi.core.database.exception.DatabaseException
import com.viktoriiaroi.core.database.model.NewsEntity
import com.viktoriiaroi.core.model.News
import com.viktoriiaroi.core.network.NewsService
import com.viktoriiaroi.core.network.model.news.NewsDTO
import com.viktoriiaroi.core.utils.processList
import com.viktoriiaroi.core.utils.processResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao,
) {
    suspend fun getNews(): Result<List<News>> {
        val networkResult = getNewsFromNetwork()
        networkResult.onSuccess {
            deleteNewsInDatabase()
            insertNewsToDatabase(it)
        }

        val databaseResult = getNewsFromDatabase()
        if (databaseResult.isSuccess) {
            return databaseResult
        }

        val exception = networkResult.exceptionOrNull() ?: DatabaseException.EmptyData
        return Result.failure(exception)
    }

    private suspend fun getNewsFromNetwork(): Result<List<NewsDTO>> =
        processResponse({ newsService.getNews() }, { it })

    private suspend fun getNewsFromDatabase(): Result<List<News>> =
        newsDao.getNews().processList { News.fromEntity(it) }

    private suspend fun insertNewsToDatabase(newsList: List<NewsDTO>) {
        newsDao.insertNews(newsList.map { NewsEntity.fromDTO(it) })
    }

    private suspend fun deleteNewsInDatabase() {
        newsDao.deleteNews()
    }
}
