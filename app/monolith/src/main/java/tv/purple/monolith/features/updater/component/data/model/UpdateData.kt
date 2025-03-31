package tv.purple.monolith.features.updater.component.data.model

import android.os.Parcel
import android.os.Parcelable

data class UpdateData(
    val build: Int,
    val codename: String,
    val url: String,
    val logoUrl: String?,
    val changelog: String?,
    val size: Long = -1
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(build)
        parcel.writeString(codename)
        parcel.writeString(url)
        parcel.writeString(logoUrl)
        parcel.writeString(changelog)
        parcel.writeLong(size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UpdateData> {
        override fun createFromParcel(parcel: Parcel): UpdateData {
            return UpdateData(parcel)
        }

        override fun newArray(size: Int): Array<UpdateData?> {
            return arrayOfNulls(size)
        }
    }
}
