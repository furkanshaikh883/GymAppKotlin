package com.example.gymappkotlin.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.gymappkotlin.R


class KcsProgressDialog(context: Context, isCancelable: Boolean) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.kcs_progress_dialog)
        setCancelable(isCancelable)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
    }
}