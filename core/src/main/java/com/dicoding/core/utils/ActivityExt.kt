package com.dicoding.core.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dicoding.core.R

@Suppress("DEPRECATION")
fun Activity.setWhiteStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // for set light status bar
        window.decorView.systemUiVisibility = flags
        window.statusBarColor = Color.WHITE
    }
}

fun Activity.startActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

@Suppress("DEPRECATION")
fun Activity.setBlackStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = window.decorView.systemUiVisibility
        flags = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // for clear light status bar
        window.decorView.systemUiVisibility = flags
        window.statusBarColor = getColor(R.color.colorPrimaryDark)
    }
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.removeObservers(this)
    liveData.observe(this, Observer(body))
}
