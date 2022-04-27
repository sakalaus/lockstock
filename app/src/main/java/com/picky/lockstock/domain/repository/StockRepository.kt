package com.picky.lockstock.domain.repository

import com.picky.lockstock.domain.model.CompanyInfoModel
import com.picky.lockstock.domain.model.CompanyListingModel
import com.picky.lockstock.domain.model.IntradayDataModel
import com.picky.lockstock.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayDataModel>>?

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfoModel>

}