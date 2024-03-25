package com.sabinetek.vesta.data.repository

import com.sabinetek.vesta.data.api.AuthService
import com.sabinetek.vesta.data.api.model.NetworkResult
import com.sabinetek.vesta.data.api.model.NetworkResult.Companion.safeApiCall
import com.sabinetek.vesta.data.api.model.req.Login
import com.sabinetek.vesta.data.api.model.req.Sms
import com.sabinetek.vesta.data.api.model.resp.Api
import com.sabinetek.vesta.data.api.model.resp.User
import javax.inject.Inject


class AuthRepository @Inject  constructor(
    private val service: AuthService,
) {
    suspend fun senSms(sms: Sms): NetworkResult<Api<Any>> =
        safeApiCall { service.sendSms(sms) }

    suspend fun login(login: Login): NetworkResult<Api<User>> =
        safeApiCall { service.login(login) }
}