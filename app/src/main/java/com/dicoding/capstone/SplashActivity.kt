package com.dicoding.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.core.utils.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        navigateToMovie()
    }

    private fun navigateToMovie() {
        startActivity(MainActivity::class.java)
        finish()
    }
}