package com.test.rxjava2.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.test.rxjava2.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var adapter: BooksListAdapter? = null
    private var model: BooksViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = BooksListAdapter()
        bookList!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        bookList!!.adapter = adapter
        searchButton!!.setOnClickListener {v ->
            if (isConnectionAvailable()) {
                performSearch()
            } else {
                Toast.makeText(this@MainActivity, "No internet", Toast.LENGTH_SHORT).show()
            }
        }

        model = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        model!!.getBooks().observe(this, Observer { it ->
            it?.let {
                adapter!!.setBooksList(it)
            }
        })
    }

    private fun performSearch() {
        val formatUserInput = searchText!!.text.toString().trim { it <= ' ' }.replace("\\s+".toRegex(), "+")
        model!!.loadBooks(formatUserInput)
    }

    private fun isConnectionAvailable(): Boolean {

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.isConnected) {
                // connected to wifi
            }
        }
        return activeNetwork.isConnected
    }
}
