package com.test.rxjava2.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.test.rxjava2.model.Book
import com.test.rxjava2.model.BookSearchResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BooksViewModel : ViewModel() {

    private var books: MutableLiveData<List<Book>>? = null
    internal var booksAction: BooksAction = BooksAction()

    internal fun getBooks(): LiveData<List<Book>> {
        if (null == books) {
            books = MutableLiveData()
        }
        return books as MutableLiveData<List<Book>>
    }

    fun setBooks(books: MutableLiveData<List<Book>>) {
        this.books = books
    }

    fun loadBooks(query: String) {
        booksAction.getBooks(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookSearchResult> {
                    override fun onSubscribe(@NonNull d: Disposable) {

                    }

                    override fun onNext(@NonNull bookSearchResult: BookSearchResult) {
                        books!!.value = bookSearchResult.books
                    }

                    override fun onError(@NonNull e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {

                    }
                })
    }
}