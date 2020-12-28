package com.android.bookmanager_kotlin.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.DatePickerUtils.showDatePicker
import kotlinx.android.synthetic.main.activity_add_book.*
import java.lang.Exception

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


    // アクションバー戻るボタンクリック処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
                finish()

                return true
         }
        return super.onOptionsItemSelected(item)
    }
}
