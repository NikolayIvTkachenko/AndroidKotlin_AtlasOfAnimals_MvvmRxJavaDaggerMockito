package com.rsh_engineering.tkachenkoni.atlasofanimals.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

@Module
class PrefsModule {

    @Singleton
    @Provides
    @TypeOfContext(CONTEXT_APP)
    fun provideSharedPreferences(app: Application): SharedPreferencesHelper{
        return SharedPreferencesHelper(app)
    }

    @Singleton
    @Provides
    @TypeOfContext(CONTEXT_ACTIVITY)
    fun provideActivitySharedPreferences(activity: AppCompatActivity): SharedPreferencesHelper{
        return SharedPreferencesHelper(activity)
    }
}

const val CONTEXT_APP = "Application context"
const val CONTEXT_ACTIVITY ="Activity context"

@Qualifier
annotation class TypeOfContext(val type: String)