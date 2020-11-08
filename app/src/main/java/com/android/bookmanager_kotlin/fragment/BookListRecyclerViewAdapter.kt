package com.android.bookmanager_kotlin.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bookmanager_kotlin.R

class BookListRecyclerViewAdapter (private val bookListData: MutableList<MutableMap<String, String>>): RecyclerView.Adapter<BookListRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_book_list, parent, false)
        return BookListRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {
        val item = bookListData[position]
        // TODO: Bookクラス作る
        // TODO: 画像データも表示させる
        val bookName = item["bookName"] ?: ""
        val bookPrice = item["bookPrice"] ?: ""
        val bookPurchaseDate = item["bookPurchaseDate"] ?: ""

        holder.bookName.text = bookName
        holder.bookPrice.text = bookPrice
        holder.bookPurchaseDate.text = bookPurchaseDate
    }

    override fun getItemCount(): Int = bookListData.size
}

// 外部クラスとしてViewHoldeを設置
class BookListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var bookName: TextView = itemView.findViewById(R.id.tv_book_name)
    var bookPrice: TextView = itemView.findViewById(R.id.tv_book_price)
    var bookPurchaseDate: TextView = itemView.findViewById(R.id.tv_book_purchase_date)
}
