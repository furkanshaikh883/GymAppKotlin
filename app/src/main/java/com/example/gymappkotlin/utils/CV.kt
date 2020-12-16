package com.example.gymappkotlin.utils

import android.os.Environment
import com.example.gymappkotlin.BuildConfig
import com.example.gymappkotlin.utils.CV.BASE_URL
import java.io.File
import java.util.*

object CV {

    var BASE_URL = BuildConfig.BASE_URL
    var SP_ISLOGIN = "SP_ISLOGIN"
    val APP_FOLDER_NAME = ".smttown"
    val IMAGE_SAVE_FORMAT = "MM_dd_yyyy_hh_mm_a"
    val DOWNLOADFILEPATH = Environment.getExternalStorageDirectory()
        .path + File.separator + APP_FOLDER_NAME
    val PREF_ACCESS_TOKEN: String = "PREFS_TOKEN"
    val HEADER_CONTENT_KEY = "Content-Type"
    val VERSION = "Version"
    val VERSION_NAME = "V1"
    val DOMAIN_NAME = "DomainCode"
    val DOMAIN = "smtqc"
    val USERTOKEN = "UserToken"
    val AUTHORIZATION = "Authorization"
    val HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded"
    val PREF_APPLICANT_STATUS: String = "applicantStatus"
    val LOCALE_USE_DATEFORMAT = Locale.US
    val SP_SELECTED_LANGUAGE = "SP_SELECTED_LANGUAGE"
    val USERNAME = "userName"
    val password = "password"
    val DomainCode ="DomainCode"
    val Authtoken = "token"
    val User = "user"
    val LoggedUser = "loggedUser"
    val LIST = "list"
    val TITLE = "title"
    val SupportTypes = "types"
    val Statustype = "statustype"
    val companyName = "companyName"
    val supportTypeId = "supportypeid"
    val statusTypeId = "status"

}
