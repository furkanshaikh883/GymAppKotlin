package com.example.gymappkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityBeginScreenBinding
import com.example.gymappkotlin.databinding.ActivityPersonalDetailBinding
import com.example.gymappkotlin.viewmodel.LoginViewModel
import com.example.gymappkotlin.viewmodel.PersonalDetailViewModel

class PersonalDetailActivity : BaseActivity<ActivityPersonalDetailBinding>() {

    var viewModel: PersonalDetailViewModel? = null
    lateinit var binding: ActivityPersonalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_personal_detail
        )
        setListner()
        setData()

    }

    private fun setData() {
        viewModel = ViewModelProviders.of(this).get(PersonalDetailViewModel::class.java)
    }

    private fun setListner() {
        binding.back.setOnClickListener() {
            finish()
        }

        binding.next.setOnClickListener(){
            val intent = Intent(this@PersonalDetailActivity, BioDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }
}