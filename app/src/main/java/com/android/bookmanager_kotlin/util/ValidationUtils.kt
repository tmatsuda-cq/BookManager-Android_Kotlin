package com.android.bookmanager_kotlin.util

import android.util.Patterns
import com.android.bookmanager_kotlin.R
import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {

    // ログイン
    fun validationCheckLogin(email: String, password: String) : Int? {
        if (email.isEmpty() || password.isEmpty()) {
            return R.string.error_empty_form
        } else if (isInvalidEmail(email) || isInvalidPassword(password)) {
            return R.string.error_email_or_password
        }
        return null
    }

    // サインアップ
    fun validationCheckSignup(email: String, password: String, passwordConfirmation: String) : Int? {
        if (email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            return R.string.error_empty_form
        } else if (isInvalidEmail(email) || isInvalidPassword(password)) {
            return R.string.error_email_or_password
        } else if (isNoMatchPassword(password, passwordConfirmation)) {
            return R.string.error_equal_password
        }
        return null
    }

    // 書籍登録
    // バリデーションメソッド
    fun validationCheckBookData(name: String, price: String, purchaseDate: String) : Int? {
        if (name.isEmpty() || price.isEmpty() || purchaseDate.isEmpty()) {
            return R.string.error_empty_form
        } else if (isNumber(price)) {
            return R.string.error_book_price
        }
        return null
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

    // 数値かどうかをチェック
    private fun isNumber(price: String) : Boolean {
        return !Pattern.compile("^[0-9]+$").matcher(price).matches()
    }
}
