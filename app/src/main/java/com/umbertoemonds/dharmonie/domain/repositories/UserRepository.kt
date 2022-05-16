package com.umbertoemonds.dharmonie.domain.repositories

import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    suspend fun login(loginData: LoginData): Single<String>
    suspend fun getDetails(): Single<UserData>
}