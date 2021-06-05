package com.zeekands.catsapp.di

import com.zeekands.core.domain.usecase.CatAppUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DynamicFeatureDependencies {

    fun sampleRepository(): CatAppUseCase
}