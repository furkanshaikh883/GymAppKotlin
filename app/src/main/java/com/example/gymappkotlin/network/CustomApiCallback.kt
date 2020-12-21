package com.example.gymappkotlin.network

import com.example.gymappkotlin.model.ResponseModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class CustomApiCallback<T : ResponseModel> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {

        val data = getResponse(response, ResponseModel::class.java as Class<T>)
        val isError = isErrorInWebResponse(data!!.status)

        if (isError) {            // if error found display popup with message
            if (data.message.isNotEmpty()) {
                showErrorMessage(data.message)
            } else {
                showErrorMessage("Something went wrong. Please try again")
            }
        } else {
            handleResponseData(response.body())
        }

        handleResponseData(response.body())

    }

    protected abstract fun handleResponseData(data: T?)

    protected abstract fun showErrorMessage(errormessage: String?)

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (isKnownException(t)) {
            showErrorMessage("Due to network connection error we\\'re having trouble.")
        } else {
            showErrorMessage("Something went wrong. Please try again")
        }
    }

    //for showing known exception
    private fun isKnownException(t: Throwable): Boolean {
        return (t is ConnectException
                || t is UnknownHostException
                || t is SocketTimeoutException
                || t is IOException)
    }

    // check whether is there any error in web response
    private fun isErrorInWebResponse(statusCode: Int): Boolean {
        // Handler handler = new Handler();
        val isError: Boolean = when (statusCode) {
            200 //success
            -> false
            400 -> true
            401 -> true
            404 -> true
            500 -> true
            else -> true
        }
        return isError
    }

    //Checking response code if not proper or null then showing this message
    private fun <T> getResponse(tResponse: Response<T>, tClass: Class<T>): T? {
        if (tResponse.code() in 200..299) {
            var t = tResponse.body()

            if (t == null) {
                t = GsonBuilder().create().fromJson(createErrorMsgJson(), tClass)
            }
            return t
        } else {
            val errorConverter =
                RestClient.retrofitClient.responseBodyConverter<T>(tClass, arrayOfNulls(0))
            try {
                return errorConverter.convert(tResponse.errorBody()!!)
            } catch (e: IOException) {
                e.printStackTrace()
                return GsonBuilder().create().fromJson(createErrorMsgJson(), tClass)
            }

        }
    }

    private fun createErrorMsgJson(): String {
        return "\n" +
                "{\n" +
                "  \"Status\": true,\n" +
                "  \"StatusCode\": 0,\n" +
                "  \"Message\": \"Due to network connection error we\\'re having trouble\",\n" +
                "  \"MemberLeaveListResultModel\": {\n" +
                "  \n" +
                "  }\n" +
                "}"
    }
}