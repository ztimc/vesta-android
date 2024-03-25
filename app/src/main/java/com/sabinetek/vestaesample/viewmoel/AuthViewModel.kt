package com.sabinetek.vestaesample.viewmoel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabinetek.vesta.data.api.model.req.Sms
import com.sabinetek.vesta.data.repository.AuthRepository
import com.sabinetek.vesta.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun sendSms() {
        viewModelScope.launch(dispatcher) {

            Timber.i("fucker !!")
            val result = repository.senSms(Sms("18501052331"))
            Timber.e("fucker !! ${result.data?.msg}")
        }
    }



}