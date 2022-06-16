package com.umbertoemonds.dharmonie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository
import com.umbertoemonds.dharmonie.domain.usecases.LoginUseCase
import com.umbertoemonds.dharmonie.domain.usecases.UseCase
import com.umbertoemonds.dharmonie.domain.usecases.UseCaseHandler

class LoginViewModel (private val useCaseHandler: UseCaseHandler, private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(loginData: LoginData, callback: UserRepository.LoginCallback){
        val requestValue = LoginUseCase.RequestValues(loginData)

        useCaseHandler.execute(loginUseCase, requestValue, object : UseCase.UseCaseCallback<LoginUseCase.ResponseValue>{
            override fun onSuccess(response: LoginUseCase.ResponseValue) {
                callback.onLoggedIn(response.token)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }
        })
    }

}