package com.picky.lockstock.data.csv

import com.opencsv.CSVReader
import com.picky.lockstock.data.mappers.toIntradayInfo
import com.picky.lockstock.data.remote.dto.IntradayDataDto
import com.picky.lockstock.domain.model.CompanyListingModel
import com.picky.lockstock.domain.model.IntradayDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject

class IntradayDataParser @Inject constructor(): CSVParser<IntradayDataModel> {

    override suspend fun parse(stream: InputStream): List<IntradayDataModel> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull {line ->
                    val timeStamp = line.getOrNull(0)?: return@mapNotNull null
                    val close = line.getOrNull(4)?: return@mapNotNull null
                    val dto = IntradayDataDto(timeStamp, close.toDouble())
                    dto.toIntradayInfo()
                }
                .filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}