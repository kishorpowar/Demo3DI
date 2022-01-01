package com.kishor.demo3di.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "last_name")
    val lastName: String
): Parcelable
