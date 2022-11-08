package com.viktoriiaroi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.database.model.NewsEntity

@Database(entities = [NewsEntity::class, CompanyEntity::class], version = 1, exportSchema = true)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun getCompanyDao(): CompanyDao
    abstract fun getNewsDao(): NewsDao
}
