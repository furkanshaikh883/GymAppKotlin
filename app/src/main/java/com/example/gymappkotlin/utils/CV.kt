package com.example.gymappkotlin.utils

import android.os.Environment
import com.example.gymappkotlin.BuildConfig
import com.example.gymappkotlin.utils.CV.BASE_URL
import java.io.File
import java.util.*

object CV {

    var BASE_URL = BuildConfig.BASE_URL
    val UNAUTHORIZED_CODE = 401
    val INTERNAL_SERVER_ERROR_CODE = 500
    val NO_DATA_FOUND_CODE = 404
    val METHOD_NOT_ALLOW = 405
    val RESPONSE_MESSAGE = "message"
    val APP_FOLDER_NAME = ".smttown"
    val IMAGE_SAVE_FORMAT = "MM_dd_yyyy_hh_mm_a"
    val DOWNLOADFILEPATH = Environment.getExternalStorageDirectory()
        .path + File.separator + APP_FOLDER_NAME
    val HEADER_CONTENT_KEY = "Content-Type"
    val VERSION = "Version"
    val VERSION_NAME = "V1"
    val DOMAIN_NAME = "DomainCode"
    val DOMAIN = "smtqc"
    val USERTOKEN = "UserToken"
    val AUTHORIZATION = "Authorization"
    val HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded"
    val LOCALE_USE_DATEFORMAT = Locale.US
    val SP_SELECTED_LANGUAGE = "SP_SELECTED_LANGUAGE"
    val Authtoken = "token"
    val User = "user"
    val SupportTypes = "types"
    val Statustype = "statustype"
    val companyName = "companyName"
    val supportTypeId = "supportypeid"
    val statusTypeId = "status"
    val REQUEST_EMAIL = "email"
    val REQUEST_PASSWORD = "password"


}
