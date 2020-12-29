package com.android.bookmanager_kotlin.activity

import android.R.id.home
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.R.id.bt_save
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.regex.Matcher
import java.util.regex.Pattern

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
                // TODO: バリデーションチェックを挟む
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                val passwordConfirmation = et_password_confirmation.text.toString()

                if (email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                    Toast.makeText(this, "未入力項目があります", Toast.LENGTH_LONG).show()
                } else if (isInvalidEmail(email) || isInvalidPassword(password)) {
                    Toast.makeText(this, "メールアドレスまたはパスワードの値が不正です", Toast.LENGTH_LONG).show()
                } else if (isNoMatchPassword(password, passwordConfirmation)) {
                    Toast.makeText(this, "パスワードが一致していません", Toast.LENGTH_LONG).show()
                } else {
                    startActivity(Intent(this, BookListActivity::class.java))
                    finish()

                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // invalid: 無効, valid: 有効
    private fun isInvalidEmail(email: String) : Boolean {
        val matcher: Matcher = Patterns.EMAIL_ADDRESS.matcher(email)
        return !matcher.matches()
    }

    private fun isInvalidPassword(password: String) : Boolean{
        // ^: 行の先頭 {6,}: 直前の文字を6個以上繰り返しと一致
        // 行の先頭が「.」or 6文字同一の文字列の場合は false を返す
        val pattern: Pattern = Pattern.compile("^.{6,}")
        val matcher: Matcher = pattern.matcher(password)
        return !matcher.matches()
    }

    // パスワードと確認用パスワードが一致するかを確認
    private fun isNoMatchPassword(password: String, passwordConfirmation: String) : Boolean{
        if (password == passwordConfirmation) {
            return false
        }
        return true
    }
}
