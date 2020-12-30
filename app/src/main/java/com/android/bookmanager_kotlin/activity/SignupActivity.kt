package com.android.bookmanager_kotlin.activity

import android.R.id.home
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.R.id.bt_save
import com.android.bookmanager_kotlin.util.ValidationUtils
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //アクションバーと戻るボタン
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
            home -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()

                return true
            }
            bt_save -> {
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                val passwordConfirmation = et_password_confirmation.text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckSignup(email, password, passwordConfirmation)

                if (errorMessage == null) {
                    startActivity(Intent(this, BookListActivity::class.java))
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
