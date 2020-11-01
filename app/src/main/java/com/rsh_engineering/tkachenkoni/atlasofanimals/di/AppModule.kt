package com.rsh_engineering.tkachenkoni.atlasofanimals.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */

@Module
class AppModule (val app: Application) {

    @Provides
    fun providesApp() : Application = app

}