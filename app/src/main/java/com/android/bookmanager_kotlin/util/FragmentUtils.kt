package com.android.bookmanager_kotlin.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

// TODO: 共通化処理 object 式で定義した方が良いのか？ / companion object で定義した方が良いのか確認
class FragmentUtils {

    fun showFragment(fragment: Fragment, fragmentManager: FragmentManager, @IdRes container: Int) {
        fragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
