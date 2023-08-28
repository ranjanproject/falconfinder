package com.example.falconfinder.models

import android.os.Parcel
import android.os.Parcelable

data class PlanetResponseItem(
    val distance: Int,
    val name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlanetResponseItem

        if (distance != other.distance) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = distance
        result = 31 * result + name.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(distance)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlanetResponseItem> {
        override fun createFromParcel(parcel: Parcel): PlanetResponseItem {
            return PlanetResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<PlanetResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}