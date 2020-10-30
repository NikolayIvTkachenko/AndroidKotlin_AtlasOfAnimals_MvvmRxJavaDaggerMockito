package com.rsh_engineering.tkachenkoni.atlasofanimals.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel

/**
 *
 * Created by Nikolay Tkachenko on 30, October, 2020
 *
 */
class ListViewModel(application: Application): AndroidViewModel(application) {

    val animals by lazy{
        MutableLiveData<List<AnimalModel>>()
    }

    val loadError by lazy {MutableLiveData<Boolean>()}
    val loading by lazy {MutableLiveData<Boolean>()}

    fun refresh(){
        getAnimals()
    }

    private fun getAnimals(){
        val a1 = AnimalModel("alligator")
        val a2 = AnimalModel("cat")
        val a3 = AnimalModel("bee")
        val a4 = AnimalModel("dog")
        val a5 = AnimalModel("elephant")
        val a6 = AnimalModel("tiger")
        val a7 = AnimalModel("flaming")

        val animalList = arrayListOf(a1, a2, a3, a4, a5, a6, a7)

        animals.value = animalList
        loadError.value = false
        loading.value = false

    }

}