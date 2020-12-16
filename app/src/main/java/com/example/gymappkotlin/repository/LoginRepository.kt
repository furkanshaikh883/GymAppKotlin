package com.example.gymappkotlin.repository

import androidx.lifecycle.MutableLiveData
import com.example.gymappkotlin.model.DataWrapper
import com.example.gymappkotlin.model.LoginModel
import com.example.gymappkotlin.model.ResponseModel
import com.example.gymappkotlin.network.CustomApiCallback
import com.example.gymappkotlin.network.RestClient
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    fun callLogin(inputParam: JsonObject): MutableLiveData<DataWrapper<LoginModel>> {
        val dataWrapper = DataWrapper<LoginModel>()
        val dashbordResponse = MutableLiveData<DataWrapper<LoginModel>>()
        RestClient.getService()?.calllLogin(inputParam)?.enqueue(object : CustomApiCallback<LoginModel>() {
            override fun handleResponseData(data: LoginModel?) {
                dataWrapper.data=data
                dashbordResponse.value = dataWrapper
            }
            override fun showErrorMessage(errormessage: String?) {
                dataWrapper.message = errormessage
                dataWrapper.data = null
                dashbordResponse.value=dataWrapper
            }
        })
        return dashbordResponse
    }
}