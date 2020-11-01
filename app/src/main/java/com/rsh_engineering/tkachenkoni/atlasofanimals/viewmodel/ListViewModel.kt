package com.rsh_engineering.tkachenkoni.atlasofanimals.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.data.NetworkApiService
import com.rsh_engineering.tkachenkoni.atlasofanimals.di.*
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.ApiKey
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.SharedPreferencesHelper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 * Created by Nikolay Tkachenko on 30, October, 2020
 *
 */

class ListViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean = true): this(application){
        injected = test
    }


    val animals by lazy{
        MutableLiveData<List<AnimalModel>>()
    }

    val loadError by lazy {MutableLiveData<Boolean>()}
    val loading by lazy {MutableLiveData<Boolean>()}

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiNetwork : NetworkApiService

    @Inject
    @field:TypeOfContext(CONTEXT_APP)
    lateinit var prefs : SharedPreferencesHelper

    private var invalidApiKey = false

    private var injected = false

    fun inject() {
        if(!injected) {
            DaggerViewModelComponent.builder()
                .appModule(AppModule(getApplication()))
                .build()
                .inject(this)
        }
    }

    fun refresh(){
        inject()
        loading.value = true
        invalidApiKey = false
        val key = prefs.getApiKey()
        if(key.isNullOrEmpty()){
            getKey()
        }else{
            getAnimals(key)
        }
    }

    fun hardRefresh(){
        inject()
        loading.value = true
        getKey()
    }

    private fun getKey(){
        disposable.add(
            apiNetwork.getApiKey()
                .subscribeOn(Schedulers.io())
                //.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ApiKey>(){
                    override fun onSuccess(key: ApiKey) {
                        if(key.key.isNullOrEmpty()){
                            loadError.value = true
                            loading.value = false
                        }else{
                            prefs.saveApiKey(key.key)
                            getAnimals(key.key)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }

                })
        )
    }


    private fun getAnimals(key:String){

        disposable.add(
            apiNetwork.getAnimals(key)
                .subscribeOn(Schedulers.io())
                //.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<AnimalModel>>(){
                    override fun onSuccess(list: List<AnimalModel>) {
                        loadError.value = false
                        animals.value = list
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        if(!invalidApiKey){
                            invalidApiKey = true
                            getKey()
                        }else{
                            e.printStackTrace()
                            loading.value = false
                            animals.value = null
                            loadError.value = true
                        }

                    }

                })
        )
    }




    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


//    private fun getAnimals(){
//        val a1 = AnimalModel("alligator")
//        val a2 = AnimalModel("cat")
//        val a3 = AnimalModel("bee")
//        val a4 = AnimalModel("dog")
//        val a5 = AnimalModel("elephant")
//        val a6 = AnimalModel("tiger")
//        val a7 = AnimalModel("flaming")
//
//        val animalList = arrayListOf(a1, a2, a3, a4, a5, a6, a7)
//
//        animals.value = animalList
//        loadError.value = false
//        loading.value = false
//    }

}