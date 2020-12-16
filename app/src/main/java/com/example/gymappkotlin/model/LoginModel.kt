package com.example.gymappkotlin.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

data class LoginModel(
    val data: Data
) :ResponseModel()  {
    data class Data(
        var age: Int,
        var city: String,
        var email: String,
        var fullName: String,
        var gender: String,
        var id: Int,
        var isMerchant: Boolean,
        var mobile: String,
        var noOfUserVoted: Int,
        var password: String,
        var profileImage: String,
        var token: String,
        var totalRatingsReceived: Int,
        var userType: String
    )
}