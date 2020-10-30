package com.rsh_engineering.tkachenkoni.atlasofanimals.data

import com.rsh_engineering.tkachenkoni.atlasofanimals.BASE_URL
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.ApiKey
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by Nikolay Tkachenko on 31, October, 2020
 *
 */
class NetworkApiService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NetworkApi::class.java)

    fun getApiKey(): Single<ApiKey>{
        return api.getApiKey()
    }

    fun getAnimals(key: String):Single<List<AnimalModel>>{
        return api.getAnimals(key)
    }

}