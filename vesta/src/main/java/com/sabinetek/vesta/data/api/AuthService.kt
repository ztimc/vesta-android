package com.sabinetek.vesta.data.api

import com.sabinetek.vesta.data.api.model.req.Login
import com.sabinetek.vesta.data.api.model.req.Sms
import com.sabinetek.vesta.data.api.model.resp.Api
import com.sabinetek.vesta.data.api.model.resp.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/user/sms")
    suspend fun sendSms(
        @Body sms: Sms
    ): Response<Api<Any>>

    @POST("/user/login")
    suspend fun login(
        @Body login: Login
    ): Response<Api<User>>

}