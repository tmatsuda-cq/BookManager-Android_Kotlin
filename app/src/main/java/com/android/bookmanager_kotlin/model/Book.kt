package com.android.bookmanager_kotlin.model

data class Book(
    // TODO: API実装の際にidも追加する
    var bookName: String,
    var bookPrice: Int,
    var date: String,
    var bookImage: String?
)

