package com.viktoriiaroi.core.database

import androidx.room.*
import com.viktoriiaroi.core.database.model.launch.AllLaunchID
import com.viktoriiaroi.core.database.model.launch.FutureLaunchID
import com.viktoriiaroi.core.database.model.launch.PastLaunchID

@Dao
abstract class LaunchIDsDao {
    @Transaction
    open suspend fun clearAndInsertAllIDs(list: List<AllLaunchID>) {
        clearAllIDs()
        insertAllIDs(list)
    }

    @Transaction
    open suspend fun clearAndInsertPastIDs(list: List<PastLaunchID>) {
        clearPastIDs()
        insertPastIDs(list)
    }

    @Transaction
    open suspend fun clearAndInsertFutureIDs(list: List<FutureLaunchID>) {
        clearFutureIDs()
        insertFutureIDs(list)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllIDs(list: List<AllLaunchID>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPastIDs(list: List<PastLaunchID>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFutureIDs(list: List<FutureLaunchID>)

    @Query("DELETE FROM all_ids")
    abstract suspend fun clearAllIDs()

    @Query("DELETE FROM past_ids")
    abstract suspend fun clearPastIDs()

    @Query("DELETE FROM future_ids")
    abstract suspend fun clearFutureIDs()
}