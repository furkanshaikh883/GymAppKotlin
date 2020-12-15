package com.example.gymappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityBioDetailsBinding
import com.example.gymappkotlin.databinding.ActivityPaymentDetailBinding

class PaymentDetailActivity  : BaseActivity<ActivityPaymentDetailBinding>() {


    lateinit var binding: ActivityPaymentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_payment_detail
        )
    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

}