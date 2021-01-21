package com.dicoding.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.dicoding.core.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImageUrl(url: String?, isFromBase: Boolean = true) {
    if (url.isNullOrBlank()) return
    val fullPath = if (isFromBase) BASE_IMAGE_URL + url else url
    Picasso.get()
        .load(fullPath)
        .placeholder(R.color.athens_gray)
        .fit()
        .centerCrop()
        .into(this)
}

fun ShimmerFrameLayout.isShimmerStart(isShimmer: Boolean) =
    if (isShimmer) startShimmer() else stopShimmer()


fun ProgressBar.setProgressRating(stringRate: String?) {
    stringRate ?: return
    val rate = (stringRate.toDoubleOrNull() ?: 0.0) * 10
    val res = context.resources
    progress = rate.toInt()
    progressDrawable = when (rate) {
        in 80.0..100.0 -> ResourcesCompat.getDrawable(
            res,
            R.drawable.bg_circular_progress_green,
            null
        )
        in 51.0..79.9 -> ResourcesCompat.getDrawable(
            res,
            R.drawable.bg_circular_progress_yellow,
            null
        )
        else -> ResourcesCompat.getDrawable(res, R.drawable.bg_circular_progress_red, null)
    }
}

fun View.isGone(isGone: Boolean){
    visibility = if (isGone) View.GONE else View.VISIBLE
}

fun TextView.formatDate(date: String?){
    if (date.isNullOrBlank()) {
        text = ""
        return
    }
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
    val formattedDate = formatter.format(parser.parse(date) ?: "")
    text = formattedDate
}