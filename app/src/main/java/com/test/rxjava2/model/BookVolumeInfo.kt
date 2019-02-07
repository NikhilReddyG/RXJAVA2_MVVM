package com.test.rxjava2.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class BookVolumeInfo : Parcelable {

    @SerializedName("authors")
    var author: ArrayList<String>? = null
    @SerializedName("title")
    var title: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeStringList(this.author)
        dest.writeString(this.title)
    }

    protected constructor(`in`: Parcel) {
        this.author = `in`.createStringArrayList()
        this.title = `in`.readString()
    }

    companion object {

        @SuppressLint("ParcelCreator")
        val CREATOR: Parcelable.Creator<BookVolumeInfo> = object : Parcelable.Creator<BookVolumeInfo> {
            override fun createFromParcel(source: Parcel): BookVolumeInfo {
                return BookVolumeInfo(source)
            }

            override fun newArray(size: Int): Array<BookVolumeInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}