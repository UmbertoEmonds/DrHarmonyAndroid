package com.umbertoemonds.dharmonie.domain.repositories

import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData

interface UserRepository {

    fun login (loginData: LoginData, callback: LoginCallback)
    fun getUserDetails(callback: GetDetailsCallback)

    interface LoginCallback {
        fun onLoggedIn(token: String)
        fun onError(t: Throwable)
    }

    interface GetDetailsCallback {
        fun onDetailsLoaded(userData: UserData)
        fun onError(t: Throwable)
    }

}