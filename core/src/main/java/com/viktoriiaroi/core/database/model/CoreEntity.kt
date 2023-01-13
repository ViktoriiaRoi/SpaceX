package com.viktoriiaroi.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viktoriiaroi.core.model.Core
import com.viktoriiaroi.core.model.Status

@Entity(tableName = "cores")
class CoreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "serial")
    var serial: String,
    @ColumnInfo(name = "status")
    var status: Status,
    @ColumnInfo(name = "reused")
    var reused: Boolean,
    @ColumnInfo(name = "block")
    var block: Int? = null,
) {
    companion object {
        fun fromModel(src: Core) = CoreEntity(
            id = src.id,
            serial = src.serial,
            status = src.status,
            block = src.block,
            reused = src.reused
        )
    }
}