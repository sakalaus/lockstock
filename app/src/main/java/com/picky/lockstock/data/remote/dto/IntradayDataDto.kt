package com.picky.lockstock.data.remote.dto

import com.squareup.moshi.Json

data class IntradayDataDto(
    val timeStamp: String,
    val close: Double
)
