package com.picky.lockstock.di

import com.picky.lockstock.data.csv.CSVParser
import com.picky.lockstock.data.csv.CompanyListingsParser
import com.picky.lockstock.data.csv.IntradayDataParser
import com.picky.lockstock.data.repository.StockRepositoryImpl
import com.picky.lockstock.domain.model.CompanyListingModel
import com.picky.lockstock.domain.model.IntradayDataModel
import com.picky.lockstock.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListingModel>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayDataParser: IntradayDataParser
    ): CSVParser<IntradayDataModel>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}