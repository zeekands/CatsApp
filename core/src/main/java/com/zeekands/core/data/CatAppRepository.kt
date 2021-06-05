package com.zeekands.core.data

import com.zeekands.core.data.source.local.LocalDataSource
import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.data.source.remote.RemoteDataSource
import com.zeekands.core.data.source.remote.network.ApiResponse
import com.zeekands.core.data.source.remote.response.ListCatsResponse
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.domain.repository.ICatRepository
import com.zeekands.core.utils.AppExecutors
import com.zeekands.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatAppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatRepository {

    override fun getAllCats(): Flow<Resource<List<Cat>>> =
        object : NetworkBoundResource<List<Cat>, ListCatsResponse>() {
            override suspend fun saveCallResult(data: ListCatsResponse) {
                val catsList = DataMapper.mapGameResponsesToEntities(data)
                localDataSource.insertCats(catsList)
            }

            override suspend fun createCall(): Flow<ApiResponse<ListCatsResponse>> {
                return remoteDataSource.getGames()
            }

            override fun loadFromDB(): Flow<List<Cat>> {
                return localDataSource.getAllCats().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Cat>?): Boolean {
                return data == null || data.isEmpty()
            }
        }.asFlow()

    override fun getFavoriteCats(): Flow<List<Cat>> {
        return localDataSource.getAllFavoriteCats().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchCats(search: String): Flow<List<Cat>> {
        return localDataSource.getCatSearch(search).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setCatFavorite(Cat: Cat, state: Boolean) {
        val catEntity = DataMapper.mapDomainToEntity(Cat)
        appExecutors.diskIO().execute {
            localDataSource.setCatFavorite(catEntity, state)
        }
    }

    override fun getCat(idDb: Int): Flow<CatEntity> {
        return localDataSource.getCat(idDb)
    }
}