package com.viktoriiaroi.core.database.model.launch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "past_ids")
class PastLaunchID (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String
)