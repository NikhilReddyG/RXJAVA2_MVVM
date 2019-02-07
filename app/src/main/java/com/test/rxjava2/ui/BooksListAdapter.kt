package com.test.rxjava2.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.rxjava2.R
import com.test.rxjava2.model.Book
import kotlinx.android.synthetic.main.book_item.view.*
import java.util.*

class BooksListAdapter : RecyclerView.Adapter<BooksListAdapter.BooksHolder>() {

    private var booksList: List<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BooksHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        holder.bindBook(booksList[position])
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    fun setBooksList(booksList: List<Book>) {
        this.booksList = booksList
        notifyDataSetChanged()
    }

    class BooksHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindBook(book: Book) {
            val bookVolumeInfo = book.volumeInfo
            if (null != bookVolumeInfo) {
                itemView.title!!.text = bookVolumeInfo.title
                if (null != bookVolumeInfo.author) {
                    val builder = StringBuilder()
                    for (author in bookVolumeInfo.author!!) {
                        if (builder.isNotEmpty()) {
                            builder.append(", ")
                        }
                        builder.append(author)
                    }
                    itemView.author!!.text = builder.toString()
                }
            }
        }
    }
}