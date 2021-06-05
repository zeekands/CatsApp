package com.zeekands.catsapp.ui.cats

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.zeekands.core.data.Resource
import com.zeekands.core.domain.model.Cat
import com.zeekands.core.domain.usecase.CatAppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val catAppUseCase: CatAppUseCase
) : ViewModel() {

    fun getCats(): LiveData<Resource<List<Cat>>> {
        return catAppUseCase.getAllCats().asLiveData()
    }
}