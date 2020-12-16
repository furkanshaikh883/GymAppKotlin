package com.example.gymappkotlin.network

import com.example.gymappkotlin.model.*
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


/**
 **The web API interface**
 * Please define all your web API extensions here
 */
interface WebServiceAPI {

    @POST("loginUser")
    fun calllLogin(@Body jsonObject: JsonObject): Call<LoginModel>
}
