package com.rsh_engineering.tkachenkoni.atlasofanimals.data

import com.rsh_engineering.tkachenkoni.atlasofanimals.di.DaggerApiComponent
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.ApiKey
import io.reactivex.Single
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 31, October, 2020
 *
 */

class NetworkApiService {

    @Inject
    lateinit var api : NetworkApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getApiKey(): Single<ApiKey>{
        return api.getApiKey()
    }

    fun getAnimals(key: String):Single<List<AnimalModel>>{
        return api.getAnimals(key)
    }

}