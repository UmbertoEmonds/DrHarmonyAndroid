package com.umbertoemonds.dharmonie.presentation.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (private val loginUseCase: LoginUseCase) : ViewModel() {
    private val token = MutableLiveData<String>()

    fun login(loginData: LoginData) = flow<String> {
        loginUseCase.login(loginData).blockingSubscribe {
            token.postValue(it)
        }
    }
}