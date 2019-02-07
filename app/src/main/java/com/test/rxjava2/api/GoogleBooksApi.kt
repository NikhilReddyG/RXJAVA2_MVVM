package com.test.rxjava2.api

import com.test.rxjava2.model.BookSearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("books/v1/volumes")
    fun search(@Query("q") search: String): Observable<BookSearchResult>
}