package com.zeekands.catsapp.favourite.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zeekands.catsapp.favourite.ui.favourite.FavoriteViewModel
import com.zeekands.catsapp.favourite.ui.favourite.FavouriteViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

    //i have been sleep late for 4 days just to discover this method
    @Singleton
    @Provides
    fun providePostDetailViewModel(fragment: Fragment, factory: FavouriteViewModelFactory) =
        ViewModelProvider(fragment, factory).get(FavoriteViewModel::class.java)
}