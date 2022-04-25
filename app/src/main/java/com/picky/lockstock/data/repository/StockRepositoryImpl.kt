package com.picky.lockstock.data.repository
import com.picky.lockstock.data.csv.CSVParser
import com.picky.lockstock.data.csv.CompanyListingsParser
import com.picky.lockstock.data.local.StockDataBase
import com.picky.lockstock.data.mappers.toCompanyListingEntity
import com.picky.lockstock.data.mappers.toCompanyListingModel
import com.picky.lockstock.data.remote.StockAPI
import com.picky.lockstock.domain.model.CompanyListingModel
import com.picky.lockstock.domain.repository.StockRepository
import com.picky.lockstock.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockAPI,
    val db: StockDataBase,
    val companyListingsParser: CSVParser<CompanyListingModel>
    ): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>> {
        return flow {

            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map { item -> item.toCompanyListingModel() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            }
            catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Failed loading data"))
                null
            }
            catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Failed loading data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(listings.map {
                        it.toCompanyListingEntity()
                })
                emit(Resource.Success(data = dao.searchCompanyListing("")
                    .map { it.toCompanyListingModel() }))
                emit(Resource.Loading(false))
            }
        }
    }
}

