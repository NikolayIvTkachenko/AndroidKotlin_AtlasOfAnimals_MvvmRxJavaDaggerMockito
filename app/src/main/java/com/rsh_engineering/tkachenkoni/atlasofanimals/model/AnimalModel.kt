package com.rsh_engineering.tkachenkoni.atlasofanimals.model

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
)

data class Taxonomy(
    @SerializedName("kingdom")
    val kingdom: String?,
    @SerializedName("order")
    val order: String?,
    @SerializedName("family")
    val family: String?
)

data class Speed(
    @SerializedName("metric")
    val metric : String?,
    @SerializedName("imperial")
    val imperial : String?
)