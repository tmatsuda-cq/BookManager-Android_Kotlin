package com.android.bookmanager_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.DatePickerUtils.showDatePicker
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        et_add_book_purchase_date.setOnClickListener {
            showDatePicker(this, et_add_book_purchase_date)
        }
    }

    // アクションバー戻るボタンクリック処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
                finish()

                return true
         }
        return super.onOptionsItemSelected(item)
    }
}
