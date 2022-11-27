package com.viktoriiaroi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.database.model.LaunchEntity
import com.viktoriiaroi.core.database.model.NewsEntity
import com.viktoriiaroi.core.database.model.RocketEntity

@Database(entities = [CompanyEntity::class, NewsEntity::class, LaunchEntity::class, RocketEntity::class], version = 1, exportSchema = true)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun getCompanyDao(): CompanyDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getLaunchDao(): LaunchDao
    abstract fun getRocketDao(): RocketDao
}
