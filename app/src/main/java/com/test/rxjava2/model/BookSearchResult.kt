package com.test.rxjava2.model

import com.google.gson.annotations.SerializedName

class BookSearchResult {
    @SerializedName("items")
    var books: List<Book>? = null
}