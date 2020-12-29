package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import android.util.Patterns
import android.widget.Toast
import com.android.bookmanager_kotlin.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.app_login)

        // ログインボタンクリックイベント処理
        bt_login.setOnClickListener {

            // TODO: バリデーション挟む
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // TODO: Stringファイルにアクセスさせたい
                Toast.makeText(this, "未入力項目があります", Toast.LENGTH_LONG).show()
            } else if (isInvalidEmail(email) || isInvalidPassword(password)) {
                // エラートースト表示
                Toast.makeText(this, "メールアドレスまたはパスワードの値が不正です", Toast.LENGTH_LONG).show()
            } else {
                // TODO: API実装時に認証処理を挟む
                // 画面遷移処理＋ログインアクティビティを終了
                val intent = Intent(this, BookListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // 新規登録ボタンクリックイベント
        bt_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
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
}
