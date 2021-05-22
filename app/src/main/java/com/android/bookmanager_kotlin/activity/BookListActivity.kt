package com.android.bookmanager_kotlin.activity

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.fragment.BookListFragment
import com.android.bookmanager_kotlin.fragment.LogoutFragment
import com.android.bookmanager_kotlin.util.FragmentUtils
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // ボトムナビバーのクリックイベントリスナをセット
        findViewById<BottomNavigationView>(R.id.bottom_navbar).setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // fragment表示処理を記述
        FragmentUtils.openFragment(BookListFragment(), supportFragmentManager, R.id.activity_book_list_layout, false)
    }

    // ナビゲーションクリックイベントを記述
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navbar_book_list -> {
                FragmentUtils.openFragment(BookListFragment(), supportFragmentManager, R.id.activity_book_list_layout, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navbar_setting -> {
                FragmentUtils.openFragment(LogoutFragment(), supportFragmentManager, R.id.activity_book_list_layout, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    // 背景タップでキーボードを非表示
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val focusView = currentFocus ?: return false
        KeyboardUtils.hideKeyboard(focusView)

        return false
    }
}
