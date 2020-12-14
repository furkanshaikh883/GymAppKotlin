package com.example.gymappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(), View.OnClickListener {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_dashboard
        )

    }

    override fun onClick(p0: View?) {

    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }
}