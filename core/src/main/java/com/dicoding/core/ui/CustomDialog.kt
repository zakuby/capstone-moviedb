package com.dicoding.core.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.dicoding.core.R
import com.dicoding.core.databinding.DlgFavoriteAddBinding
import com.dicoding.core.databinding.DlgFavoriteRemoveBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomDialog @Inject constructor(
    val resources: Resources
) {
    private var dlg: Dialog? = null

    private fun isShowing(): Boolean = dlg?.isShowing ?: false

    fun dismiss() {
        dlg?.dismiss()
        dlg = null
    }

    private fun showDialog(
        context: Context,
        view: View,
        dismissListener: DialogInterface.OnDismissListener
    ) {
        if (isShowing()) return
        dlg = Dialog(context, R.style.AlertDialogTheme)
        dlg?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setOnDismissListener(dismissListener)
            setContentView(view)
            show()
        }
    }

    fun showRemoveFromFavoriteDialog(
        context: Context,
        dismissListener: DialogInterface.OnDismissListener = DialogInterface.OnDismissListener { }
    ) {
        val binding: DlgFavoriteRemoveBinding =
            DlgFavoriteRemoveBinding.inflate(LayoutInflater.from(context))
        showDialog(context, binding.root, dismissListener)
    }

    fun showAddToFavoriteDialog(
        context: Context,
        dismissListener: DialogInterface.OnDismissListener = DialogInterface.OnDismissListener { }
    ) {
        val binding: DlgFavoriteAddBinding =
            DlgFavoriteAddBinding.inflate(LayoutInflater.from(context))
        showDialog(context, binding.root, dismissListener)
    }
}
