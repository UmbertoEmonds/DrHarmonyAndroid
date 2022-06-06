package com.umbertoemonds.dharmonie.utils.injection

import com.umbertoemonds.dharmonie.data.repositories.UserRepositoryImpl
import com.umbertoemonds.dharmonie.domain.usecases.LoginUseCase
import com.umbertoemonds.dharmonie.domain.usecases.UseCaseHandler

object Injection {

    fun provideLoginUseCase() : LoginUseCase = LoginUseCase(provideUserRepository())
    fun provideUserCaseHandler() : UseCaseHandler = UseCaseHandler.getInstance()

    private fun provideUserRepository(): UserRepositoryImpl {
        return UserRepositoryImpl.getInstance(ApiModule.create())
    }

}