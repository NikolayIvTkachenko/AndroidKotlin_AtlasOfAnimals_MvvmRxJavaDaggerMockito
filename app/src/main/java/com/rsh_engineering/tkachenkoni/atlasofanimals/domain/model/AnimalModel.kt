package com.rsh_engineering.tkachenkoni.atlasofanimals.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Nikolay Tkachenko on 30, October, 2020
 *
 */

//{"message":"OK","key":"b6f396f0bf599375124f9b1a6da2e79a03b9c1e9"}
data class ApiKey(
    @SerializedName("message")
    val message:String?,
    @SerializedName("key")
    val key:String?
)

data class AnimalModel(
    @SerializedName("name")
    val name: String?,
    @SerializedName("taxonomy")
    val taxonomy: Taxonomy?,
    @SerializedName("location")
    val location : String?,
    @SerializedName("speed")
    val speed: Speed?,
    @SerializedName("diet")
    val diet: String?,
    @SerializedName("lifespan")
    val lifeSpan: String?,
    @SerializedName("image")
    val imageUrl: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Taxonomy::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Speed::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(taxonomy, flags)
        parcel.writeString(location)
        parcel.writeParcelable(speed, flags)
        parcel.writeString(diet)
        parcel.writeString(lifeSpan)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimalModel> {
        override fun createFromParcel(parcel: Parcel): AnimalModel {
            return AnimalModel(parcel)
        }

        override fun newArray(size: Int): Array<AnimalModel?> {
            return arrayOfNulls(size)
        }
    }
}

data class Taxonomy(
    @SerializedName("kingdom")
    val kingdom: String?,
    @SerializedName("order")
    val order: String?,
    @SerializedName("family")
    val family: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(kingdom)
        parcel.writeString(order)
        parcel.writeString(family)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Taxonomy> {
        override fun createFromParcel(parcel: Parcel): Taxonomy {
            return Taxonomy(parcel)
        }

        override fun newArray(size: Int): Array<Taxonomy?> {
            return arrayOfNulls(size)
        }
    }
}

data class Speed(
    @SerializedName("metric")
    val metric : String?,
    @SerializedName("imperial")
    val imperial : String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(metric)
        parcel.writeString(imperial)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Speed> {
        override fun createFromParcel(parcel: Parcel): Speed {
            return Speed(parcel)
        }

        override fun newArray(size: Int): Array<Speed?> {
            return arrayOfNulls(size)
        }
    }
}

data class AnimalePalette(var color: Int)