package com.example.gymappkotlin.activity

import android.os.Bundle
import android.os.Handler
import com.example.gymappkotlin.R
import com.example.gymappkotlin.databinding.ActivitySplashBinding
import com.example.gymappkotlin.utils.CM
import com.example.gymappkotlin.utils.CV

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onRequestGranted(requestCode: Int, perms: List<String>) {
    }

    private lateinit var mActivity: SplashActivity
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    private var islogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        CM.removeStatusBar(mActivity)
        bindView(R.layout.activity_splash)
        init()

    }

    private fun init() {
        mHandler = Handler()
        mRunnable = Runnable {
            if (CM.getSp(this, CV.Authtoken,"") !=null && !CM.getSp(this, CV.Authtoken,"")!!.equals("")) {
                CM.startActivity(mActivity, DashboardActivity::class.java)
            } else {
                CM.startActivity(mActivity, BeginScreenActivity::class.java)
            }
            CM.finishActivity(mActivity)
        }
        mHandler.postDelayed(mRunnable, 4000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }


}
