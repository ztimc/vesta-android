package com.sabinetek.vestaesample.viewmoel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabinetek.vesta.data.api.model.NetworkResult
import com.sabinetek.vesta.data.api.model.req.Sms
import com.sabinetek.vesta.data.repository.AuthRepository
import com.sabinetek.vesta.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _isSendMsg = MutableStateFlow(false)
    val isSendMsg: MutableStateFlow<Boolean> = _isSendMsg

    private val _intervalSecond = MutableStateFlow(60)
    val intervalSecond: MutableStateFlow<Int> = _intervalSecond


    private var job: Job? = null

    fun sendSms(phone: String) {
        viewModelScope.launch(dispatcher) {
            when (val result = repository.senSms(Sms(phone))) {
                is NetworkResult.Success -> {
                    _isSendMsg.value = true
                    viewModelScope.launch(dispatcher) {
                        repeat(60) {
                            delay(1000L)
                            _intervalSecond.value--
                            if (_intervalSecond.value != 0) {
                                intervalSecond.value = 60
                                _isSendMsg.value = false
                            }
                        }
                    }
                }

                is NetworkResult.Empty -> TODO()
                is NetworkResult.Error -> TODO()
                is NetworkResult.Loading -> TODO()
            }
        }
    }


}