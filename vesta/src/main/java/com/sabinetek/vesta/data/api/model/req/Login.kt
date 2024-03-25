package com.sabinetek.vesta.data.api.model.req


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "phoneNumber")
    val phoneNumber: String,
    @Json(name = "smsCode")
    val smsCode: String
)