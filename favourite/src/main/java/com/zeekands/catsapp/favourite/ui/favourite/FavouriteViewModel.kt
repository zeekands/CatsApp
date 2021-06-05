package com.zeekands.catsapp.favourite.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.domain.usecase.CatAppUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val catAppUseCase: CatAppUseCase
) : ViewModel() {

    fun getFavouriteCats(): LiveData<List<Cat>> =
        catAppUseCase.getFavoriteCats().asLiveData()
}