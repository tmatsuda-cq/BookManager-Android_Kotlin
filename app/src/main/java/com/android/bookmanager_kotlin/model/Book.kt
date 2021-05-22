package com.android.bookmanager_kotlin.model

data class Book(
    // TODO: API実装の際にidも追加する
    var name: String,
    var price: Int,
    var date: String,
    var image: String?
)

