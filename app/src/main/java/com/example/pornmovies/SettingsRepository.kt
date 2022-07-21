package com.example.pornmovies

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SettingsRepository {
    lateinit var sharedPreferences: SharedPreferences
    fun getSort(context: Context):String{
        sharedPreferences = context.getSharedPreferences("sort",MODE_PRIVATE)
        return sharedPreferences.getString("sort","name").toString()
    }
    fun putSort(smth:String){
        sharedPreferences.edit().clear().commit()
        sharedPreferences.edit().putString("sort",smth).commit()
    }
}