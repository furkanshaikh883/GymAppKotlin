package com.example.gymappkotlin.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(message:String) {
    val spannableStringBuilder = SpannableStringBuilder(message);
    spannableStringBuilder.setSpan(RelativeSizeSpan(1.35f), 0, message.length, 0)
    Toast.makeText(this,spannableStringBuilder, Toast.LENGTH_LONG).show()
}

/**
 * Show Visibility of a [View]
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Hide Visibility of a [View]
 */
fun View.hide() {
    this.visibility = View.GONE
}


/**
 * Invisible Visibility of a [View]
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Makes SnackBar which comes from Bottom for the Short duration
 */
fun makeSnackBar(text: String, view: View?) {
    val snackBar = view?.let { Snackbar.make(it, text, Snackbar.LENGTH_LONG) }
    snackBar?.show()
}

