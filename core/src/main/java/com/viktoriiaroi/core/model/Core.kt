package com.viktoriiaroi.core.model

import com.viktoriiaroi.core.database.model.CoreEntity
import com.viktoriiaroi.core.network.model.core.CoreDTO

class Core(
    var id: String,
    var serial: String,
    var status: Status,
    var reused: Boolean,
    var block: Int? = null,
) {
    companion object {
        fun fromDTO(src: CoreDTO) = Core(
            id = src.id.orEmpty(),
            serial = src.serial.orEmpty(),
            status = Status.valueOf(src.status?.uppercase() ?: "UNKNOWN"),
            block = src.block,
            reused = (src.reuseCount ?: 0) > 0
        )

        fun fromEntity(src: CoreEntity) = Core(
            id = src.id,
            serial = src.serial,
            status = src.status,
            block = src.block,
            reused = src.reused
        )
    }
}