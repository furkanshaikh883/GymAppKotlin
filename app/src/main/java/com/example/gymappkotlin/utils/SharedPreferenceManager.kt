package com.example.gymappkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.gymappkotlin.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceManager(val context: Context) {
    lateinit var mySharedPreference: SharedPreferences
    val myPreference = "MyPreference"
    val mode: Int = Activity.MODE_PRIVATE

    fun setAuthToken(accessToken: String,context: Context) {
        mySharedPreference = context.getSharedPreferences(myPreference, mode)
        mySharedPreference.edit().putString(CV.Authtoken,accessToken).apply()
    }

    fun getAuthToken(context: Context): String? {
        mySharedPreference = context.getSharedPreferences(myPreference, mode)
        return mySharedPreference.getString(CV.Authtoken, "")
    }

    fun setUserData(userData: User?, context: Context) {
        val gson = Gson()
        val type = object : TypeToken<User>() {
        }.type
        val strObject = gson.toJson(userData, type)
        mySharedPreference = context.getSharedPreferences(myPreference, mode)
        mySharedPreference.edit().putString(CV.User, strObject).apply()
    }

    fun getUserData(context: Context): User {
        val gson = Gson()
        val type = object : TypeToken<User>() {}.type
        mySharedPreference = context.getSharedPreferences(myPreference, mode)
        return gson.fromJson(mySharedPreference.getString(CV.User, null), type)
    }


}