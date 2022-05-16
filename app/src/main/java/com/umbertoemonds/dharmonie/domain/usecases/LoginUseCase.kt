package com.umbertoemonds.dharmonie.domain.usecases

import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val apiRepository: UserRepository) {
    suspend fun login(loginData: LoginData) = apiRepository.login(loginData)
    suspend fun getDetails() = apiRepository.getDetails()
}