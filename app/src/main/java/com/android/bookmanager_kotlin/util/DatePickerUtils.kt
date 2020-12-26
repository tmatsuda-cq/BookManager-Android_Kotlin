package com.android.bookmanager_kotlin.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.util.*

object DatePickerUtils {

    fun showDatePicker(context: Context, editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context, { _, y, m, d ->
                editText.setText("$y/${m + 1}/$d")
            }, year, month, dayOfMonth
        ).show()
    }
}
