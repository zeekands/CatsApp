package com.zeekands.core.domain.usecase

import com.zeekands.core.data.Resource
import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatAppUseCase {

    fun getAllCats(): Flow<Resource<List<Cat>>>

    fun getFavoriteCats(): Flow<List<Cat>>

    fun getSearchCats(search: String): Flow<List<Cat>>

    fun setCatFavorite(Cat: Cat, state: Boolean)

    fun getCat(idDb: Int): Flow<CatEntity>
}