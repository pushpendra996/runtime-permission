package com.example.mvvm.vm.activity

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.mvvm.helper.IPermissions
import com.example.mvvm.view.activity.BaseActivity
import com.example.mvvm.view.activity.MainActivity
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Pushpendra Saini on 10/02/24 @ 5:15 pm
 * Contact - sainipushpendra51@gmail.com
 * Copyright ©2024 Pushpendra
 */
class MainVM : ViewModel() {
    fun onTapFabIcon(view: View) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    fun onTapPermission(view: View) {
        var activity = view.context.scanForActivity()
        activity?.checkPermission(permissionCallBack)
    }

    private var permissionCallBack = object : IPermissions {
        override fun permitted() {
            Log.d("TAG", "permitted: ")
        }
    }

    fun Context.scanForActivity(): MainActivity? {
        return when (this) {
            is MainActivity -> this
            is ContextWrapper -> baseContext.scanForActivity()
            else -> null
        }
    }

}