package com.android.bookmanager_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.DatePickerUtils.showDatePicker
import com.android.bookmanager_kotlin.util.FragmentUtils
import kotlinx.android.synthetic.main.fragment_edit_book.*

class EditBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_book, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_edit_book)
            it.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        // 書籍一覧から渡されたデータをviewに表示する
        setFragmentResultListener("bookData") { _, bundle ->
            et_edit_book_name.setText(bundle.getString("bookName"))
            et_edit_book_price.setText(bundle.getInt("bookPrice").toString())
            et_edit_book_purchase_date.setText(bundle.getString("bookPurchaseDate"))
        }

        return view
    }

    // onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()
    override fun onStart() {
        super.onStart()

        // onCreateView()で呼ぶとnullポになる
        et_edit_book_purchase_date.setOnClickListener {
            showDatePicker(requireContext(), et_edit_book_purchase_date)
        }
    }

    // アクションバー戻るボタンクリックでフラグメント切り替え
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            FragmentUtils().showFragment(BookListFragment(), parentFragmentManager, R.id.fl_activity_book_list)
        }
        return super.onOptionsItemSelected(item)
    }
}
