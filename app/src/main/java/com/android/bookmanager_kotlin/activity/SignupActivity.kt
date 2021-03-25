package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.AlertDialogUtils.showAlertDialog
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.android.bookmanager_kotlin.util.ValidationUtils

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setTitle(R.string.app_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // アクションバー保存ボタンを表示
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    // アクションバーボタンクリック処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()

                return true
            }
            R.id.bt_save -> {
                val email = findViewById<EditText>(R.id.input_email).text.toString()
                val password = findViewById<EditText>(R.id.input_password).text.toString()
                val passwordConfirmation = findViewById<EditText>(R.id.input_password_confirmation).text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckSignup(email, password, passwordConfirmation)

                if (errorMessage == null) {
                    val intent = Intent(this, BookListActivity::class.java)
                    // activityを起動する前に既存タスクを破棄。LoginActivity・SignupActivity 両方が終了される
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    showAlertDialog(R.string.dialog_title, errorMessage)
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
