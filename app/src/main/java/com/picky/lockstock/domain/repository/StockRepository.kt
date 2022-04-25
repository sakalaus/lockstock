package com.picky.lockstock.domain.repository

import com.picky.lockstock.domain.model.CompanyListingModel
import com.picky.lockstock.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>>

}