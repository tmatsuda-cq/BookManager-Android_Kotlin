package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.android.bookmanager_kotlin.R

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
            android.R.id.home -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

                return true
            }
            R.id.bt_save -> {
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
                finish()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
