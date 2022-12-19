package com.viktoriiaroi.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriiaroi.core.database.model.launch.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {
    @Query("SELECT launches.* FROM all_ids JOIN launches ON launches.id = all_ids.id")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Query("SELECT launches.* FROM past_ids JOIN launches ON launches.id = past_ids.id")
    fun getPastLaunches(): Flow<List<LaunchEntity>>

    @Query("SELECT launches.* FROM future_ids JOIN launches ON launches.id = future_ids.id")
    fun getFutureLaunches(): Flow<List<LaunchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(list: List<LaunchEntity>)
}