package com.example.gymappkotlin.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymappkotlin.model.DataWrapper
import com.example.gymappkotlin.model.LoginModel
import com.example.gymappkotlin.model.ResponseModel
import com.example.gymappkotlin.repository.LoginRepository
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody

class LoginViewModel : ViewModel(){

    private var model : MutableLiveData<DataWrapper<LoginModel>>? = null
    private val repository = LoginRepository()

    fun callLogin(inputParam: JsonObject): MutableLiveData<DataWrapper<LoginModel>>? {
        model = repository.callLogin(inputParam)
        return model
    }


}