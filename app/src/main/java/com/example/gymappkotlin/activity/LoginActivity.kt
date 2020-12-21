package com.example.gymappkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityLoginBinding
import com.example.gymappkotlin.model.LoginModel
import com.example.gymappkotlin.utils.CM
import com.example.gymappkotlin.utils.CV
import com.example.gymappkotlin.utils.LogM
import com.example.gymappkotlin.utils.toast
import com.example.gymappkotlin.viewmodel.LoginViewModel
import com.google.gson.JsonObject
import java.util.*

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
        binding.tvForgotPassword.setOnClickListener(){
            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.tvRegister.setOnClickListener() {
            val intent = Intent(this@LoginActivity, PersonalDetailActivity::class.java)
            startActivity(intent)
        }

        binding.next.setOnClickListener() {
            val strUserName = binding.edtEmail.text.toString().trim()
            val strPassword = binding.edtPassword.text.toString().trim()

            if(strUserName.isNullOrEmpty()){
                toast(getString(R.string.enter_user_name))
            }else if (strPassword.isNullOrEmpty()) {
                toast(getString(R.string.enter_password))
            }else {
                val inputParam = JsonObject()
                inputParam.addProperty(CV.REQUEST_EMAIL, strUserName)
                inputParam.addProperty(CV.REQUEST_PASSWORD, strPassword)
                Log.e("=>"," Input param " + inputParam)

                viewModel?.callLogin(inputParam)?.observe(this, androidx.lifecycle.Observer {
                    val model: LoginModel? = it.data
                    Log.e("=>"," Input param " + it.data)
//                    CM.setSp(this,CV.Authtoken,model?.data!!)
//                    toast("API response data " + it.data)
                })
            }
        }
    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }
}