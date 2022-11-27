package com.viktoriiaroi.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriiaroi.core.database.model.RocketEntity

@Dao
interface RocketDao {
    @Query("SELECT * FROM rockets WHERE id=:id")
    suspend fun getRocket(id: String): RocketEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocket(rocket: RocketEntity)
}