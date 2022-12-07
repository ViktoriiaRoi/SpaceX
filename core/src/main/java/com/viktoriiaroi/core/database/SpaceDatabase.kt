package com.viktoriiaroi.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viktoriiaroi.core.database.model.CompanyEntity
import com.viktoriiaroi.core.database.model.CoreEntity
import com.viktoriiaroi.core.database.model.NewsEntity
import com.viktoriiaroi.core.database.model.RocketEntity
import com.viktoriiaroi.core.database.model.launch.AllLaunchID
import com.viktoriiaroi.core.database.model.launch.FutureLaunchID
import com.viktoriiaroi.core.database.model.launch.LaunchEntity
import com.viktoriiaroi.core.database.model.launch.PastLaunchID

@Database(entities = [
    CompanyEntity::class, NewsEntity::class, LaunchEntity::class, RocketEntity::class,
    CoreEntity::class, AllLaunchID::class, PastLaunchID::class, FutureLaunchID::class],
    version = 1,
    exportSchema = true)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun getCompanyDao(): CompanyDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getLaunchDao(): LaunchDao
    abstract fun getRocketDao(): RocketDao
    abstract fun getCoreDao(): CoreDao
}
