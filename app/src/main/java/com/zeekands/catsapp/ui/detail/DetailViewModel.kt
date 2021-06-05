package com.zeekands.catsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.domain.usecase.CatAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val catAppUseCase: CatAppUseCase
) : ViewModel() {

    val cat = MutableLiveData<CatEntity>()

    fun setFavoriteCat(cat: Cat, newStatus: Boolean) {
        catAppUseCase.setCatFavorite(cat, newStatus)
    }

    fun getCat(idDb: Int) = viewModelScope.launch {
        val data = catAppUseCase.getCat(idDb)
        data.collect {
            cat.postValue(it)
        }
    }

}