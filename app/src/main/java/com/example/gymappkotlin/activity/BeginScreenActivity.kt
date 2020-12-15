package com.example.gymappkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityBeginScreenBinding
import com.example.gymappkotlin.databinding.ActivityLoginBinding

class BeginScreenActivity :  BaseActivity<ActivityBeginScreenBinding>() {

    lateinit var binding: ActivityBeginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_begin_screen
        )

        setListner()
    }

    private fun setListner() {
        binding.trainerBSA.setOnClickListener(){
            val intent = Intent(this@BeginScreenActivity, PersonalDetailActivity::class.java)
            startActivity(intent)
        }

        binding. clientBSA.setOnClickListener(){
            val intent = Intent(this@BeginScreenActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

}