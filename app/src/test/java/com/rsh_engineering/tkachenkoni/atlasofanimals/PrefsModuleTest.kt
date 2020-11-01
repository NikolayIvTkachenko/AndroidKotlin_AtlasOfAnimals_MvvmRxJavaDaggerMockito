package com.rsh_engineering.tkachenkoni.atlasofanimals

import android.app.Application
import com.rsh_engineering.tkachenkoni.atlasofanimals.di.PrefsModule
import com.rsh_engineering.tkachenkoni.atlasofanimals.util.SharedPreferencesHelper

/**
 *
 * Created by Nikolay Tkachenko on 01, November, 2020
 *
 */
class PrefsModuleTest(val mockPrefs: SharedPreferencesHelper): PrefsModule() {

    override fun provideSharedPreferences(app: Application) : SharedPreferencesHelper{
        return mockPrefs
    }

}