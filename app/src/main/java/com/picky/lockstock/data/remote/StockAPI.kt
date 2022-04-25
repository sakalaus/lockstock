package com.picky.lockstock.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockAPI {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    companion object{
        const val API_KEY = "W7TSAM3N2CBJ5KRR"
        const val API_URL = "https://alphavantage.com"
    }
}