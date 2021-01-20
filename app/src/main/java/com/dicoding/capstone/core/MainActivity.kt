package com.dicoding.capstone.core

import android.view.LayoutInflater
import com.dicoding.capstone.core.base.BaseActivity
import com.dicoding.capstone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun initBinding() {}
}