package com.example.gymappkotlin.utils

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class Extension {


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

    /**
     * this method is used for showing the keyboard
     */
    fun View.showKeyboard() {

        if (requestFocus()) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }

    }

    /**
     * this method is used for hiding the keyboard into fragment
     */
    fun Fragment.hideKeyboard() {
        val activity = this.activity
        if (activity is AppCompatActivity) {
            activity.hideKeyboard()
        }
    }

    /**
     * this method is used for showing the keyboard into activity
     */
    fun AppCompatActivity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        // else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        // }
    }

    fun Context.toast(message:String) {
        val spannableStringBuilder = SpannableStringBuilder(message);
        spannableStringBuilder.setSpan(RelativeSizeSpan(1.35f), 0, message.length, 0)
        Toast.makeText(this,spannableStringBuilder, Toast.LENGTH_LONG).show()
    }


}