package com.picky.lockstock.di

import android.app.Application
import androidx.room.Room
import com.picky.lockstock.data.local.StockDataBase
import com.picky.lockstock.data.remote.StockAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockAPI(): StockAPI {
        return Retrofit.Builder()
            .baseUrl(StockAPI.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDataBase(app: Application): StockDataBase {
        return Room.databaseBuilder(
            app,
            StockDataBase::class.java,
            "stockdb.db"
        ).build()
    }

}