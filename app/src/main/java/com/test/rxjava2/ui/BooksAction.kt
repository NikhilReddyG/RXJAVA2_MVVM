package com.test.rxjava2.ui

import com.test.rxjava2.api.GoogleBooksService
import com.test.rxjava2.model.BookSearchResult
import io.reactivex.Observable

class BooksAction {
    internal fun getBooks(query: String): Observable<BookSearchResult> {
        return GoogleBooksService.INSTANCE.search("search+$query")
    }
}