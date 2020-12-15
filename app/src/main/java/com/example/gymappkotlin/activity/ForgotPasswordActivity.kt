package com.example.gymappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityForgotPasswordBinding
import com.example.gymappkotlin.databinding.ActivityLoginBinding

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>()  {


    lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_forgot_password
        )


    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

}