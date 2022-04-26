package com.picky.lockstock.domain.model

import java.time.LocalDateTime

data class IntradayDataModel(
    val date: LocalDateTime,
    val close: Double
)
