package com.android.bookmanager_kotlin.activity

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.fragment.BookListFragment
import com.android.bookmanager_kotlin.fragment.LogoutFragment
import com.android.bookmanager_kotlin.util.FragmentUtils
import com.android.bookmanager_kotlin.util.KeyboardUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_book_list.*

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // ボトムナビバーのクリックイベントリスナをセット
        bottom_navbar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // fragment表示処理を記述
        FragmentUtils.showFragment(BookListFragment(), supportFragmentManager, R.id.fl_activity_book_list)
    }

    // ナビゲーションクリックイベントを記述
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navbar_book_list -> {
                FragmentUtils.showFragment(BookListFragment(), supportFragmentManager, R.id.fl_activity_book_list)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navbar_setting -> {
                FragmentUtils.showFragment(LogoutFragment(), supportFragmentManager, R.id.fl_activity_book_list)
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
