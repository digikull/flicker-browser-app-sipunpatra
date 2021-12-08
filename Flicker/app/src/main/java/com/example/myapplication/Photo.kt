package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable


class Photo():Parcelable {
    var title: String = ""
    var photoUrl: String = ""
    var dateTaken: String = ""
    var description: String = ""
    var author: String = ""
    var tags: String = ""

    constructor(parcel: Parcel) : this() {
        title=parcel.readString()!!
        photoUrl=parcel.readString()!!
        dateTaken=parcel.readString()!!
        description=parcel.readString()!!
        author=parcel.readString()!!
        tags=parcel.readString()!!

    }

   override fun writeToParcel(parcel: Parcel,flags: Int) {
        parcel.writeString(title)
        parcel.writeString(photoUrl)
        parcel.writeString(dateTaken)
        parcel.writeString(description)
        parcel.writeString(author)
        parcel.writeString(tags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}


