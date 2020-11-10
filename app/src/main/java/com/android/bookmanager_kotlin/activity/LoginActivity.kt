package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bookmanager_kotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.app_login)

        // ログインボタンクリックイベント処理
        bt_login.setOnClickListener {
            // 画面遷移処理＋ログインアクティビティを終了
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 新規登録ボタンクリックイベント
        bt_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
