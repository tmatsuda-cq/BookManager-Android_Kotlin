package com.android.bookmanager_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.bookmanager_kotlin.R

class EditBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_book, container, false)
        activity?.setTitle(R.string.app_edit_book)
        // TODO: 戻るボタンを実装

        return view
    }
}
