package com.android.bookmanager_kotlin.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.android.bookmanager_kotlin.R
import java.util.regex.Pattern

object ValidationUtils {

    // ログイン

    // サインアップ

    // 書籍登録
    // バリデーションメソッド
    fun validationCheckBookData(name: String, price: String, purchaseDate: String) : Int? {
        if (name.isEmpty() || price.isEmpty() || purchaseDate.isEmpty()) {
            return R.string.error_empty_book_data
        } else if (isNumber(price)) {
            return R.string.error_book_price
        }
        return null
    }

    // 数値かどうかをチェック
    private fun isNumber(price: String) : Boolean {
        return !Pattern.compile("^[0-9]+$").matcher(price).matches()
    }
}
