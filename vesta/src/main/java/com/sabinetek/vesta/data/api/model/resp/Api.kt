package com.sabinetek.vesta.data.api.model.resp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Api<T>(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    var `data`: T?,
    @Json(name = "msg")
    val msg: String
)