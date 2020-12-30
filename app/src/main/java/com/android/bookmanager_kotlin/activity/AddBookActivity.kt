package com.android.bookmanager_kotlin.activity

import android.R.id.*
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.R.id.bt_save
import com.android.bookmanager_kotlin.util.DatePickerUtils.showDatePicker
import com.android.bookmanager_kotlin.util.ValidationUtils
import kotlinx.android.synthetic.main.activity_add_book.*
import java.lang.Exception
import java.util.regex.Pattern

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bt_add_book_image.setOnClickListener {
            // 推奨
            //selectBookImage()

            // TODO: 非推奨のため削除
            showImageStorage()
        }

        et_add_book_purchase_date.setOnClickListener {
            showDatePicker(this, et_add_book_purchase_date)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == 42) {
            try {
                data?.data?.also { uri ->
                    val inputStream = contentResolver?.openInputStream(uri)
                    val image = BitmapFactory.decodeStream(inputStream)
                    iv_add_book_image.setImageBitmap(image)
                }
            } catch (e: Exception) {
                Toast.makeText(this, "画像選択処理に失敗しました", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showImageStorage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, 42)
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
                            iv_add_book_image.setImageBitmap(image)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, "画像選択処理に失敗しました", Toast.LENGTH_LONG).show()
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
            home -> {
                finish()
                return true
            }
            bt_save -> {
                // TODO: API実装時に書籍データ登録処理実装
                val name = et_add_book_name.text.toString()
                val price = et_add_book_price.text.toString()
                val purchaseDate = et_add_book_purchase_date.text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckBookData(name, price, purchaseDate)

                // バリデーションに引っかかっているかをnullかどうかで判断している
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


}
