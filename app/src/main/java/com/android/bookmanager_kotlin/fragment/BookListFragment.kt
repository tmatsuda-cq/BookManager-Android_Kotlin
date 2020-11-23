package com.android.bookmanager_kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.model.Book
import kotlinx.android.synthetic.*

class BookListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragmentとlayoutを紐付ける
        // 必要？super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        activity?.setTitle(R.string.app_book_list)

        val bookListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val linearLayoutManager = LinearLayoutManager(view.context)
        val adapter = BookListRecyclerViewAdapter(createDummyBookList())

        bookListRecyclerView.layoutManager = linearLayoutManager
        bookListRecyclerView.adapter = adapter
        bookListRecyclerView.addItemDecoration(DividerItemDecoration(view.context, linearLayoutManager.orientation))

        // Item要素クリックイベント
        adapter.setOnItemClickListener(
            object : BookListRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(book: Book) {
                    // TODO: 画像データも渡せるようにする

                    // データを渡す処理
                    setFragmentResult("bookData", bundleOf(
                        "bookName" to book.bookName,
                        "bookPrice" to book.bookPrice,
                        "bookPurchaseDate" to book.date
                    ))
                    // TODO: 画面遷移処共通化したい
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_activity_book_list, EditBookFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        // アクションバーのボタンを使えるようにする処理
        setHasOptionsMenu(true)

        return view
    }

    // TODO: ダミーデータのためAPIよりデータ取得をする際は削除
    private fun createDummyBookList(): MutableList<Book> {
        var bookList: MutableList<Book> = ArrayList()
        // TODO: 画像データもインスタンスのプロパティに入れたい
        var book = Book("Kotlinスタートブック", 2800, "2020/11/24", null)

        // 20件のダミーデータを登録
        var i = 0
        while (i < 20) {
            i++
            bookList.add(book)
        }
        return bookList
    }
}
