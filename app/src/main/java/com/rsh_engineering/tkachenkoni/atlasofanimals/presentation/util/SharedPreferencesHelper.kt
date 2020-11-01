package com.rsh_engineering.tkachenkoni.atlasofanimals.presentation.util

import android.content.Context

/**
 *
 * Created by Nikolay Tkachenko on 31, October, 2020
 *
 */
class SharedPreferencesHelper(context: Context){
    private val PREF_API_KEY = "Api_key"

    //@Depricated
    //private val pref = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    private val pref = context.getSharedPreferences("AnimationAtlasAppPrefs", Context.MODE_PRIVATE);


    fun saveApiKey(key: String?){
        pref.edit().putString(PREF_API_KEY, key).apply()
    }

    fun getApiKey() = pref.getString(PREF_API_KEY, "")

}