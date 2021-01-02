package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.android.bookmanager_kotlin.util.ValidationUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.app_login)

        // ログインボタンクリックイベント処理
        bt_login.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            @StringRes
            val errorMessage = ValidationUtils.validationCheckLogin(email, password)

            if (errorMessage == null) {
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }

        // 新規登録ボタンクリックイベント
        bt_signup.setOnClickListener {
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
