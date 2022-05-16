package com.umbertoemonds.dharmonie.data.datasources

import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun login(loginData: LoginData): Flow<String>
    suspend fun getDetails(): Flow<UserData>
}