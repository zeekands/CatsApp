package com.zeekands.catsapp.favourite.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeekands.core.domain.usecase.CatAppUseCase
import javax.inject.Inject

class FavouriteViewModelFactory @Inject constructor(
    private val useCase: CatAppUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != FavoriteViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return FavoriteViewModel(
            useCase
        ) as T
    }
}