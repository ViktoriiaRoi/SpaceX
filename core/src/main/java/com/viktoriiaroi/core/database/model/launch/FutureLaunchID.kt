package com.viktoriiaroi.core.database.model.launch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "future_ids")
class FutureLaunchID (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String
)