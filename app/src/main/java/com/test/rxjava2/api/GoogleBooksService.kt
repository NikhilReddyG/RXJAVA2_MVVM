package com.test.rxjava2.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.rxjava2.BuildConfig
import com.test.rxjava2.model.BookSearchResult
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class GoogleBooksService private constructor() {

    INSTANCE;

    private val googleBooksApi: GoogleBooksApi

    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        googleBooksApi = retrofit.create(GoogleBooksApi::class.java)
    }

    fun search(search: String): Observable<BookSearchResult> {
        return googleBooksApi.search(search)
    }
}