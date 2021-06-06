package com.zeekands.catsapp.di

import com.zeekands.core.data.CatAppRepository
import com.zeekands.core.domain.repository.ICatRepository
import com.zeekands.core.domain.usecase.CatAppInteractor
import com.zeekands.core.domain.usecase.CatAppUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCatAppUsecase(iCatAppRepository: ICatRepository) =
        CatAppInteractor(iCatAppRepository) as CatAppUseCase
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun bindICatRepository(catAppRepository: CatAppRepository): ICatRepository
}