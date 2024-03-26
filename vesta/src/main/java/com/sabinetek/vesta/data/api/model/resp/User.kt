package com.sabinetek.vesta.data.api.model.resp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "expiresAt")
    val expiresAt: Long,
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: UserX
)