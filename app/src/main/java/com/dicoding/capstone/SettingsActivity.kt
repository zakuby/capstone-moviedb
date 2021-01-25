package com.dicoding.capstone

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import com.dicoding.capstone.databinding.ActivitySettingsBinding
import com.dicoding.core.base.BaseActivity
import com.dicoding.core.domain.model.Profile
import com.dicoding.core.ui.WebViewActivity
import com.dicoding.core.utils.startActivity

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivitySettingsBinding
        get() = ActivitySettingsBinding::inflate

    override fun initBinding() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_settings)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, SettingsFragment())
            .commit()
        binding.apply {
            val profile = Profile()
            profileGithub.setOnClickListener { openWebView(profile.githubUrl, "Github Profile") }
            profileDicoding.setOnClickListener { openWebView(profile.dicodingProfileUrl, "Dicoding Profile") }
            profileEmail.setOnClickListener { goToEmailIntent(profile.email) }
            profileWhatsapp.setOnClickListener { goToWhatsAppIntent(profile.phone) }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity::class.java)
        finish()
    }

    private fun goToEmailIntent(email: String) {

        val subject = "The Movie DB Android Application"
        val message = "Hi Muhammad Yaqub," + "\n" + "Nice Android Application !"

        val i = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
            type = "message/rfc822"
        }

        startActivity(Intent.createChooser(i, "Send email :"))
    }

    private fun openWebView(url: String, title: String) {
        val i = Intent(this, WebViewActivity::class.java).apply {
            putExtra("url", url)
            putExtra("title", title)
        }

        startActivity(i)
    }

    private fun goToWhatsAppIntent(phone: String) {
        val whatsAppPackageName = "com.whatsapp"
        try {
            val message = "Hi Muhammad Yaqub," + "\n" + "Nice Android Application !"
            val i = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
                putExtra("jid", "$phone@s.whatsapp.net")
                setPackage(whatsAppPackageName)
            }
            startActivity(i)
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_no_whatsapp), Toast.LENGTH_SHORT).show()
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$whatsAppPackageName")
                    )
                )
            } catch (e: android.content.ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$whatsAppPackageName")
                    )
                )
            }
        }
    }
}
