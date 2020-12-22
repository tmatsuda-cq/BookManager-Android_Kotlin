package com.android.bookmanager_kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.fragment.BookListFragment
import com.android.bookmanager_kotlin.fragment.LogoutFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // ボトムナビバーのクリックイベントリスナをセット
        bottom_navbar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // fragment表示処理を記述
        val bookListFragment = BookListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_activity_book_list, bookListFragment)
        transaction.addToBackStack(null) // 戻る処理しない場合は記述不要
        transaction.commit()
    }

    // ナビゲーションクリックイベントを記述
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // TODO: フラグメント切り替え処理共通化する
        when (item.itemId) {
            R.id.navbar_book_list -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_activity_book_list, BookListFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navbar_setting -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_activity_book_list, LogoutFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
