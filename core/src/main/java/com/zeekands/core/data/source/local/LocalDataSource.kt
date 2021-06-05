package com.zeekands.core.data.source.local

import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.data.source.local.room.CatsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: CatsDao
) {
    fun getAllCats(): Flow<List<CatEntity>> {
        return dao.getCats()
    }

    fun getCat(idDb: Int): Flow<CatEntity> = dao.getCat(idDb)

    fun getAllFavoriteCats(): Flow<List<CatEntity>> {
        return dao.getFavoriteCats()
    }

    fun getCatSearch(search: String): Flow<List<CatEntity>> {
        return dao.getSearchCats(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertCats(Cats: List<CatEntity>) = dao.insertCats(Cats)

    fun setCatFavorite(Cat: CatEntity, newState: Boolean) {
        Cat.favourite = newState
        dao.updateFavourite(Cat.idDb!!, Cat.favourite)
    }

}