package com.sabinetek.vesta.data.api.model.resp


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserX(
    @Json(name = "activeColor")
    val activeColor: String,
    @Json(name = "baseColor")
    val baseColor: String,
    @Json(name = "CreatedAt")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "enable")
    val enable: Int,
    @Json(name = "headerImg")
    val headerImg: String,
    @Json(name = "ID")
    val iD: Int,
    @Json(name = "nickName")
    val nickName: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "sideMode")
    val sideMode: String,
    @Json(name = "UpdatedAt")
    val updatedAt: String,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "uuid")
    val uuid: String
)