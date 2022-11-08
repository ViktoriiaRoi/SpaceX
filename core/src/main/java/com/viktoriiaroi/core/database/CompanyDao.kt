package com.viktoriiaroi.core.database

import androidx.room.*
import com.viktoriiaroi.core.database.model.CompanyEntity

@Dao
interface CompanyDao {
    @Query("SELECT * FROM company LIMIT 1")
    suspend fun getCompany(): CompanyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(dbCompany: CompanyEntity)
}