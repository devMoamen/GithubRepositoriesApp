package com.kryptonictest.app.ui.main

import android.os.Bundle
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseActivity
import com.kryptonictest.databinding.ActivityMainBinding
import com.kryptonictest.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}