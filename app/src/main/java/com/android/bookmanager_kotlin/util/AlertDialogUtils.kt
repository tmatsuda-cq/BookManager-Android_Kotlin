package com.android.bookmanager_kotlin.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.android.bookmanager_kotlin.R

object AlertDialogUtils {
    // Contextの拡張関数
    fun Context.showAlertDialog(@StringRes title: Int, @StringRes message: Int) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.view_ok, null)
            .show()
    }
}
