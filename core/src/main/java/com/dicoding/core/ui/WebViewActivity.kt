package com.dicoding.core.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dicoding.core.base.BaseActivity
import com.dicoding.core.databinding.ActivityWebviewBinding

class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityWebviewBinding
        get() = ActivityWebviewBinding::inflate

    @SuppressLint("SetJavaScriptEnabled")
    override fun initBinding() {
        val url = intent.getStringExtra("url") ?: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        val title = intent.getStringExtra("title")

        val toolbar = binding.toolbar.apply {
            this.title = title
            this.subtitle = url
        }

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            webViewClient = CustomWebViewClient()
            loadUrl(url)
        }
    }

    inner class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            binding.webView.loadUrl(url ?: "https://www.youtube.com/watch?v=dQw4w9WgXcQ")
            return false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.apply {
                progressBar.visibility = View.VISIBLE
                webView.visibility = View.GONE
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.apply {
                progressBar.visibility = View.GONE
                webView.visibility = View.VISIBLE
            }
        }
    }
}
