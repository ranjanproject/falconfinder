package com.example.falconfinder.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class VehicleResponseItem(
    val max_distance: Int,
    val name: String?,
    val speed: Int,
    var total_no: Int,

    //custom fields to control the state of the item
    var isSelected: Boolean = false,
    var isActive: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(max_distance)
        parcel.writeString(name)
        parcel.writeInt(speed)
        parcel.writeInt(total_no)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeByte(if (isActive) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as VehicleResponseItem

        if (max_distance != other.max_distance) return false
        if (name != other.name) return false
        if (speed != other.speed) return false
        if (total_no != other.total_no) return false
        if (isSelected != other.isSelected) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = max_distance
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + speed
        result = 31 * result + total_no
        result = 31 * result + isSelected.hashCode()
        result = 31 * result + isActive.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<VehicleResponseItem> {
        override fun createFromParcel(parcel: Parcel): VehicleResponseItem {
            return VehicleResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<VehicleResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}