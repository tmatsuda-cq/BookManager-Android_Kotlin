package com.android.bookmanager_kotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.activity.AddBookActivity
import com.android.bookmanager_kotlin.model.Book
import com.android.bookmanager_kotlin.util.FragmentUtils

class BookListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragmentとlayoutを紐付ける
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_book_list)
            it.setDisplayHomeAsUpEnabled(false)
        }

        val bookListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        val adapter = BookListRecyclerViewAdapter(createDummyBookList())

        bookListRecyclerView.layoutManager = linearLayoutManager
        bookListRecyclerView.adapter = adapter
        bookListRecyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), linearLayoutManager.orientation))

        // CellItem要素クリックイベント
        adapter.setOnItemClickListener(
            // BookListRecyclerViewAdapterで定義した抽象メソッドを実装
            // 再利用をしないため object式でインターフェースを実装
            object : BookListRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(book: Book) {
                    // TODO: API実装時画像データも渡せるようにする

                    // データを渡す処理
                    setFragmentResult("bookData", bundleOf(
                        "bookName" to book.name,
                        "bookPrice" to book.price,
                        "bookPurchaseDate" to book.date
                    ))
                    FragmentUtils.openFragment(EditBookFragment(), parentFragmentManager, R.id.activity_book_list_layout, true)
                }
            }
        )

        setHasOptionsMenu(true)

        return view
    }

    // TODO: ダミーデータのためAPIよりデータ取得をする際は削除
    private fun createDummyBookList(): MutableList<Book> {
        val bookList: MutableList<Book> = ArrayList()
        val book = Book("Kotlinスタートブック", 2800, "2020/11/24", null)
        val book2= book.copy()
        book == book2

        // 20件のダミーデータを登録
        var i = 0
        while (i < 20) {
            i++
            bookList.add(book)
        }
        return bookList
    }

    // アクションバーに追加ボタンを表示
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_add, menu)
    }

    // アクションバー追加ボタン押した時の処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bt_add -> {
                activity?.startActivity(Intent(context, AddBookActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
