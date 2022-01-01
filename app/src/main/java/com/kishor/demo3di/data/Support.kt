package com.kishor.demo3di.data

import com.squareup.moshi.Json

data class Support(
    @Json(name = "text")
    val text: String,
    @Json(name = "url")
    val url: String
)
