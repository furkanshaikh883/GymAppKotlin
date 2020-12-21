package com.example.gymappkotlin.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

data class LoginModel(
    val data: Data
) :ResponseModel()  {
    data class Data(
        var user_id: Int,
        var name: String,
        var email: String,
        var type: String,
        var token: String
    )
}