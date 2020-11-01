package com.rsh_engineering.tkachenkoni.atlasofanimals

import com.rsh_engineering.tkachenkoni.atlasofanimals.data.api.NetworkApiService
import com.rsh_engineering.tkachenkoni.atlasofanimals.di.ApiModule

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

class ApiModuleTest(val mockService: NetworkApiService): ApiModule() {

    override fun provideNetworkApiService(): NetworkApiService {
        return mockService
    }

}