package com.viktoriiaroi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viktoriiaroi.core.database.model.*

@Database(entities = [CompanyEntity::class, NewsEntity::class, LaunchEntity::class, RocketEntity::class, CoreEntity::class],
    version = 1,
    exportSchema = true)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun getCompanyDao(): CompanyDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getLaunchDao(): LaunchDao
    abstract fun getRocketDao(): RocketDao
    abstract fun getCoreDao(): CoreDao
}
