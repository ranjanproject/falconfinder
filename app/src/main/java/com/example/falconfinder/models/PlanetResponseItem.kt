package com.example.falconfinder.models

import android.os.Parcel
import android.os.Parcelable

data class PlanetResponseItem(
    val distance: Int,
    val name: String?,

    //custom field to control the state of the item
    var isSelected: Boolean = false,
    var isActive: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(distance)
        parcel.writeString(name)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeByte(if (isActive) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlanetResponseItem

        if (distance != other.distance) return false
        if (name != other.name) return false
        if (isSelected != other.isSelected) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = distance
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + isSelected.hashCode()
        result = 31 * result + isActive.hashCode()
        return result
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