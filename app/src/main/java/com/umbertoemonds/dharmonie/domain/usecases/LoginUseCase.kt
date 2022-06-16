package com.umbertoemonds.dharmonie.domain.usecases

import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository

class LoginUseCase(private val userRepository: UserRepository) : UseCase<LoginUseCase.RequestValues, LoginUseCase.ResponseValue>() {

    class RequestValues(val loginData: LoginData) : UseCase.RequestValues
    class ResponseValue(val token: String) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        requestValues?.loginData?.let {

            userRepository.login(it, object : UserRepository.LoginCallback {

                override fun onLoggedIn(token: String) {
                    val responseValue = ResponseValue(token)
                    useCaseCallback?.onSuccess(responseValue)
                }

                override fun onError(t: Throwable) {
                    useCaseCallback?.onError(t)
                }

            })
        }
    }
}