package com.rsh_engineering.tkachenkoni.atlasofanimals.di

import com.rsh_engineering.tkachenkoni.atlasofanimals.data.api.NetworkApiService
import dagger.Component

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: NetworkApiService)

}