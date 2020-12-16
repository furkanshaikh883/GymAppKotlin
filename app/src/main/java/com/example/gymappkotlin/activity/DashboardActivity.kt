package com.example.gymappkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(), View.OnClickListener {

    lateinit var binding: ActivityDashboardBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_dashboard
        )

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //Setting the navigation controller to Bottom Nav
        binding.bottomNav.setupWithNavController(navController)

        //Setting the navigation controller to Bottom Nav
        binding.bottomNav.setupWithNavController(navController)

    }

    override fun onClick(p0: View?) {

    }

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }
}