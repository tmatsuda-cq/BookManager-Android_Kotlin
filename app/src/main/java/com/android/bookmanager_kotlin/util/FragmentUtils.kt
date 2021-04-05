package com.android.bookmanager_kotlin.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {

    fun openFragment(fragment: Fragment, fragmentManager: FragmentManager, @IdRes container: Int, isAddBackStack: Boolean) {
        if (isAddBackStack) {
            fragmentManager
                    .beginTransaction()
                    .replace(container, fragment)
                    .addToBackStack(null)
                    .commit()
        } else {
            fragmentManager.popBackStack()
            fragmentManager.beginTransaction()
                    .replace(container, fragment)
                    .commit()
        }
    }
}
