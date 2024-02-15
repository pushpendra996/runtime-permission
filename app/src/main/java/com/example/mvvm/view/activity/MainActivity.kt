package com.example.mvvm.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.vm.activity.MainVM

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = ViewModelProvider(this@MainActivity)[MainVM::class.java]
            lifecycleOwner = this@MainActivity
        }

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

    }

}