package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.AlertDialogUtils.showAlertDialog
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.android.bookmanager_kotlin.util.ValidationUtils

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.app_login)

        // ログインボタンクリックイベント処理
        findViewById<Button>(R.id.login_button).setOnClickListener {
            val email = findViewById<EditText>(R.id.input_email).text.toString()
            val password = findViewById<EditText>(R.id.input_password).text.toString()

            @StringRes
            val errorMessage = ValidationUtils.validationCheckLogin(email, password)

            if (errorMessage == null) {
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showAlertDialog(R.string.dialog_title, errorMessage)
            }
        }

        findViewById<Button>(R.id.signup_button).setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 背景タップでキーボードを非表示
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val focusView = currentFocus ?: return false
        KeyboardUtils.hideKeyboard(focusView)

        return false
    }
}
