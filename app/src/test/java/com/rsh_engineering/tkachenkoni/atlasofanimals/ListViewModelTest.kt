package com.rsh_engineering.tkachenkoni.atlasofanimals

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rsh_engineering.tkachenkoni.atlasofanimals.data.NetworkApiService
import com.rsh_engineering.tkachenkoni.atlasofanimals.di.AppModule
import com.rsh_engineering.tkachenkoni.atlasofanimals.di.DaggerViewModelComponent
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.ApiKey
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.SharedPreferencesHelper
import com.rsh_engineering.tkachenkoni.atlasofanimals.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

class ListViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var networService: NetworkApiService

    @Mock
    lateinit var prefs: SharedPreferencesHelper

    val application = Mockito.mock(Application::class.java)

    var listViewModel = ListViewModel(application, true)

    private val key = "Test key"

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModuleTest(networService))
            .prefsModule(PrefsModuleTest(prefs))
            .build()
            .inject(listViewModel)
    }

    @Before
    fun setupRxSchedulers(){
        val immediate = object: Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate  }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun getAnimalsSuccess(){
        Mockito.`when`(prefs.getApiKey()).thenReturn(key) //.when(prefs.getApiKey()).thenReturn(key)
        val animal = AnimalModel("cow",
            null,
            null,
            null,
            null,
            null,
            null)
        val animalList = listOf(animal)
        val testSingle = Single.just(animalList)

        Mockito.`when`(networService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getAnimalsFailure() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(key)
        val testSingle = Single.error<List<AnimalModel>>(Throwable())
        val keySingle = Single.just(ApiKey("OK", key))

        Mockito.`when`(networService.getAnimals(key)).thenReturn(testSingle)
        Mockito.`when`(networService.getApiKey()).thenReturn(keySingle)

        listViewModel.refresh()

        Assert.assertEquals(null, listViewModel.animals.value)
        Assert.assertEquals(false, listViewModel.loading.value) //false -> true test
        Assert.assertEquals(true, listViewModel.loadError.value)
    }

    @Test
    fun getKeySuccess() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(null)
        val apiKey = ApiKey("OK", key)
        val keySingle = Single.just(apiKey)

        Mockito.`when`(networService.getApiKey()).thenReturn(keySingle)

        val animal = AnimalModel("cow", null, null, null, null, null, null)
        val animalsList = listOf(animal)
        val testSingle = Single.just(animalsList)
        Mockito.`when`(networService.getAnimals(key)).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1, listViewModel.animals.value?.size)
        Assert.assertEquals(false, listViewModel.loadError.value)
        Assert.assertEquals(false, listViewModel.loading.value)
    }
    @Test
    fun getKeyFailure() {
        Mockito.`when`(prefs.getApiKey()).thenReturn(null)
        val keySingle = Single.error<ApiKey>(Throwable())
        Mockito.`when`(networService.getApiKey()).thenReturn(keySingle)
        listViewModel.refresh()
        Assert.assertEquals(null, listViewModel.animals.value)
        Assert.assertEquals(false, listViewModel.loading.value)
        Assert.assertEquals(true, listViewModel.loadError.value)
    }

}