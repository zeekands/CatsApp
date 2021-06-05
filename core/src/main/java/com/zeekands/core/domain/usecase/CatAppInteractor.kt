package com.zeekands.core.domain.usecase

import com.zeekands.core.data.Resource
import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.domain.repository.ICatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatAppInteractor @Inject constructor(
    private val iCatRepository: ICatRepository
) : CatAppUseCase {

    override fun getAllCats(): Flow<Resource<List<Cat>>> =
        iCatRepository.getAllCats()


    override fun getFavoriteCats(): Flow<List<Cat>> =
        iCatRepository.getFavoriteCats()

    override fun getSearchCats(search: String): Flow<List<Cat>> =
        iCatRepository.getSearchCats(search)

    override fun setCatFavorite(Cat: Cat, state: Boolean) =
        iCatRepository.setCatFavorite(Cat, state)

    override fun getCat(idDb: Int): Flow<CatEntity> =
        iCatRepository.getCat(idDb)

}