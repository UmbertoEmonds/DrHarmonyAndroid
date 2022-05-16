package com.umbertoemonds.dharmonie.data.repositories

import com.umbertoemonds.dharmonie.data.ApiService
import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.data.datasources.UserDataSource
import com.umbertoemonds.dharmonie.data.models.UserData
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {

    override suspend fun login(loginData: LoginData): Single<String> {
        return apiService.login(loginData)
    }

    override suspend fun getDetails(): Single<UserData> {
        return apiService.getUserDetails()
    }

}