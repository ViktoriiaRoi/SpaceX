package com.viktoriiaroi.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viktoriiaroi.core.database.model.CoreEntity

@Dao
interface CoreDao {
    @Query("SELECT * FROM cores WHERE id=:id")
    suspend fun getCore(id: String): CoreEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCore(core: CoreEntity)
}