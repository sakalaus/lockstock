package com.picky.lockstock.data.mappers

import com.picky.lockstock.data.remote.dto.IntradayDataDto
import com.picky.lockstock.domain.model.IntradayDataModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayDataDto.toIntradayInfo(): IntradayDataModel{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timeStamp, formatter)
    return IntradayDataModel(
        close = close,
        date = localDateTime
    )
}