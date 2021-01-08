package com.android.bookmanager_kotlin.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.model.Book

class BookListRecyclerViewAdapter (
    private val bookListData: MutableList<Book>)
    : RecyclerView.Adapter<BookListRecyclerViewAdapter.BookListRecyclerViewHolder>() {

    // リスナを格納する変数を定義（インターフェースの型）
    private lateinit var listener: OnItemClickListener

    // クリックイベントリスナのインターフェースを定義
    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

    // リスナをセット
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_book_list, parent, false)
        return BookListRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {
        val item = bookListData[position]

        // TODO: API画像データも表示させる処理を実装する
        val bookName = item.bookName
        val bookPrice = item.bookPrice
        val bookPurchaseDate = item.date

        holder.bookName.text = bookName
        holder.bookPrice.text = bookPrice.toString()
        holder.bookPurchaseDate.text = bookPurchaseDate

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = bookListData.size

    class BookListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bookName: TextView = itemView.findViewById(R.id.tv_book_name)
        var bookPrice: TextView = itemView.findViewById(R.id.tv_book_price)
        var bookPurchaseDate: TextView = itemView.findViewById(R.id.tv_book_purchase_date)
        var bookImage: ImageView = itemView.findViewById(R.id.iv_book_image)
    }
}
