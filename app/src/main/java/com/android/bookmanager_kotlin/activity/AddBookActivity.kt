package com.android.bookmanager_kotlin.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.DatePickerUtils
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.android.bookmanager_kotlin.util.ValidationUtils
import java.lang.Exception

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        setTitle(R.string.app_add_book)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.add_book_image_button).setOnClickListener {
            selectBookImage()
        }

        findViewById<EditText>(R.id.input_book_purchase_date).setOnClickListener {
            DatePickerUtils.showDatePicker(this, findViewById(R.id.input_book_purchase_date))
        }
    }

    private fun selectBookImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            if (result?.resultCode == Activity.RESULT_OK) {
                result.data?.let { data: Intent ->
                    try {
                        data?.data?.also { uri ->
                            val inputStream = contentResolver?.openInputStream(uri)
                            val image = BitmapFactory.decodeStream(inputStream)
                            findViewById<ImageView>(R.id.book_image).setImageBitmap(image)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, R.string.error_insert_book_image, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        launcher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    // アクションバー戻るボタンクリック処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.bt_save -> {
                val name = findViewById<EditText>(R.id.input_book_name).text.toString()
                val price = findViewById<EditText>(R.id.input_book_price).text.toString()
                val purchaseDate = findViewById<EditText>(R.id.input_book_purchase_date).text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckBookData(name, price, purchaseDate)

                // バリデーションに引っかかっているかをerrorMessageがnullかどうかで判断
                if (errorMessage == null) {
                    finish()
                } else {
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 背景タップでキーボードを非表示
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val focusView = currentFocus ?: return false
        KeyboardUtils.hideKeyboard(focusView)

        return false
    }
}
