package com.android.bookmanager_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.fragment.BookListFragment
import kotlinx.android.synthetic.main.activity_login.*

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // fragment表示処理を記述
        val bookListFragment = BookListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_activity_book_list, bookListFragment)
        transaction.addToBackStack(null) // 戻る処理しない場合は記述不要
        transaction.commit()
    }
}
