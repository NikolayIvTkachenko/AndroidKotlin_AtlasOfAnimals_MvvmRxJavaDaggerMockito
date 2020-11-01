package com.rsh_engineering.tkachenkoni.atlasofanimals.di

import com.rsh_engineering.tkachenkoni.atlasofanimals.BASE_URL
import com.rsh_engineering.tkachenkoni.atlasofanimals.data.NetworkApi
import com.rsh_engineering.tkachenkoni.atlasofanimals.data.NetworkApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

@Module
open class ApiModule {


    @Provides
    fun provideNetworkApi() : NetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

    @Provides
    open fun provideNetworkApiService() : NetworkApiService{
        return NetworkApiService()
    }


}