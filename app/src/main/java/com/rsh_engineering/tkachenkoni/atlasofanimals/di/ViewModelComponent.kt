package com.rsh_engineering.tkachenkoni.atlasofanimals.di

import com.rsh_engineering.tkachenkoni.atlasofanimals.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

@Singleton
@Component(modules = [
    ApiModule::class,
    PrefsModule::class,
    AppModule::class
])
interface ViewModelComponent {

    fun inject(viewModel : ListViewModel)

}