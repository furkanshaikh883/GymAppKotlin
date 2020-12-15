package com.example.gymappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityDashboardBinding
import com.example.gymappkotlin.databinding.ActivityLoginBinding
import com.example.gymappkotlin.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    var viewModel: LoginViewModel? = null
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_login
        )

        setListner()
        setData()

    }

    private fun setData() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun setListner() {

    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }
}