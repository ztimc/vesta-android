package com.sabinetek.vesta.data.api.model.req


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sms(
    @Json(name = "phoneNumber")
    val phoneNumber: String
)