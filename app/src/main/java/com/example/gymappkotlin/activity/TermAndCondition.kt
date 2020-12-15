package com.example.gymappkotlin.activity

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityTermAndConditionBinding

class TermAndCondition : BaseActivity<ActivityTermAndConditionBinding>() {

    lateinit var binding: ActivityTermAndConditionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_term_and_condition
        )

        setListner()

    }

    private fun setListner() {
        binding.submitTCA.setOnClickListener() {
            if (binding.checkboxTCA.isChecked()) {
                onBackPressed()
            }else {
                Toast.makeText(this@TermAndCondition, "Please agree to term & condition", Toast.LENGTH_LONG).show()
            }
        }

        binding.backTCA.setOnClickListener() {
            onBackPressed()
        }

        binding.checkboxTCA.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked -> binding.checkboxTCA.setChecked(isChecked) }
        )
    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

}