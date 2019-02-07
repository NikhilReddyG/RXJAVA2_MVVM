package com.test.rxjava2.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Book : Parcelable {

    @SerializedName("volumeInfo")
    var volumeInfo: BookVolumeInfo? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(this.volumeInfo, flags)
    }

    protected constructor(`in`: Parcel) {
        this.volumeInfo = `in`.readParcelable(BookVolumeInfo::class.java.getClassLoader())
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<Book> = object : Parcelable.Creator<Book> {
            override fun createFromParcel(source: Parcel): Book {
                return Book(source)
            }

            override fun newArray(size: Int): Array<Book?> {
                return arrayOfNulls(size)
            }
        }
    }
}