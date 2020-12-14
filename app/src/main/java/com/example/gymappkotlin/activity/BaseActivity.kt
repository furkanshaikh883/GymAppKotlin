package com.example.gymappkotlin.activity

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.gymappkotlin.R
import com.example.gymappkotlin.utils.CM
import com.example.gymappkotlin.utils.KcsProgressDialog
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(), EasyPermissions.PermissionCallbacks  {
    private var errorSnackbar: Snackbar? = null
    private val localFragmentManager = supportFragmentManager

    lateinit var mBinding: DB
    private var kcsDialog: KcsProgressDialog? = null
    var mPreferenceManager: CM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    protected fun bindView(layout: Int) {
        mBinding = DataBindingUtil.setContentView(this, layout)

    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }


    fun showKcsDialog() {
        if (isFinishing) {
            return
        }
        runOnUiThread(Runnable {
            if (isFinishing) {
                return@Runnable
            }

            //if dialog not dismiss then first dialog=null.so not to show anytimewhen activtiy finish...
            if (kcsDialog != null) {
                kcsDialog = null
            }
            if (kcsDialog == null)
                kcsDialog = KcsProgressDialog(this@BaseActivity, false)
            if (kcsDialog != null && !kcsDialog!!.isShowing)
                kcsDialog!!.show()
        })
    }
    fun dismissKcsDialog() {
        if (isFinishing) {
            return
        }
        if (kcsDialog != null && kcsDialog!!.isShowing) {
            kcsDialog!!.dismiss()
            kcsDialog = null
        }
    }

    abstract fun onRequestGranted(requestCode: Int, perms: List<String>)

    fun hasStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    fun hasCameraPermission(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            CM.showMessageOK(this, "", resources.getString(R.string.permission_for_useapp), null)
        }
    }



}