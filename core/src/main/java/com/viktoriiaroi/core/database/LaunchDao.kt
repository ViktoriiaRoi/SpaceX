package com.viktoriiaroi.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriiaroi.core.database.model.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launches")
    suspend fun getAllLaunches(): List<LaunchEntity>

    @Query("SELECT * FROM launches WHERE NOT upcoming")
    suspend fun getPastLaunches(): List<LaunchEntity>

    @Query("SELECT * FROM launches WHERE upcoming")
    suspend fun getFutureLaunches(): List<LaunchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launchList: List<LaunchEntity>)
}