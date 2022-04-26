package com.picky.lockstock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntity: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings()

    @Query("""
            SELECT * 
            FROM companylistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:searchString) || '%' OR
                UPPER(:searchString) == symbol
            """)
    suspend fun searchCompanyListing(searchString: String): List<CompanyListingEntity>

    @Query("SELECT * FROM companylistingentity")
    suspend fun showAllCompanyListing(): List<CompanyListingEntity>

}