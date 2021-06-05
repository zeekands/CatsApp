package com.zeekands.catsapp.favourite.ui.favourite

import com.zeekands.catsapp.di.DynamicFeatureDependencies
import dagger.Component

//hilt does not support dynamic feature so yes we need to do this to make this work
@Component(dependencies = [DynamicFeatureDependencies::class])
interface DfmDaggerComponent {

    fun inject(fragment: FavouriteFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeatureDependencies): DfmDaggerComponent
    }
}