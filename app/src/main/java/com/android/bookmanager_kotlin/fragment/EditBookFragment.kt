package com.android.bookmanager_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.model.Book
import kotlinx.android.synthetic.main.fragment_edit_book.*

class EditBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_book, container, false)
        activity?.setTitle(R.string.app_edit_book)
        // TODO: 戻るボタンを実装

        // 書籍一覧から渡されたデータをviewに表示する
        setFragmentResultListener("bookData") { _, bundle ->
            et_edit_book_name.setText(bundle.getString("bookName"))
            et_edit_book_price.setText(bundle.getInt("bookPrice").toString())
            et_edit_book_purchase_date.setText(bundle.getString("bookPurchaseDate"))
        }

        return view
    }
}
