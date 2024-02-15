package com.example.mvvm.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mvvm.helper.IPermissions

/**
 * Created by Pushpendra Saini on 14/02/24 @ 5:06 pm
 * Contact - sainipushpendra51@gmail.com
 * Copyright ©2024 Pushpendra
 */
open class BaseActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var permissionCallback: IPermissions
    fun checkPermission(callback: IPermissions) {
        permissionCallback = callback
        if (hasPermission()) {
            callback.permitted()
        } else {
            var isUINeedToShow = false
            for (permission in permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    isUINeedToShow = true
                }
            }
            if (isUINeedToShow) {
                //You Need to Show the information why you are required this permission!
            } else {
                permissionRequestLauncher.launch(permissions)
            }
        }
    }

    private fun hasPermission(): Boolean {
        var isPermission = true
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                isPermission = false
            }
        }
        return isPermission
    }

    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (!it.value) {
                    isGranted = false
                }
            }

            if (isGranted) {
                permissionCallback.permitted()
            } else {
                //You Need to Show the information why you are required this permission!
            }
        }

}
















