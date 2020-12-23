package com.android.bookmanager_kotlin.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentUtils {

    fun showFragment(fragment: Fragment, fragmentManager: FragmentManager, @IdRes container: Int) {
        fragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
