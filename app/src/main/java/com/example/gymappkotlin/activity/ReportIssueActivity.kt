package com.example.gymappkotlin.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityReportIssueBinding

class ReportIssueActivity  : BaseActivity<ActivityReportIssueBinding>() {

    lateinit var binding: ActivityReportIssueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_report_issue
        )

        setListner()

    }

    private fun setListner() {
        binding.back.setOnClickListener() {
            onBackPressed()
        }

        binding.submit.setOnClickListener() {
            Toast.makeText(this@ReportIssueActivity, "API call is pending", Toast.LENGTH_LONG).show()
        }

    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

}