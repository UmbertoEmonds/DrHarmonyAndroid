package com.umbertoemonds.dharmonie.data.repositories

import com.umbertoemonds.dharmonie.data.ApiService
import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.domain.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl constructor (private val apiService: ApiService) : UserRepository {

    companion object {
        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(apiService: ApiService) : UserRepositoryImpl {
            if(INSTANCE == null){
                INSTANCE = UserRepositoryImpl(apiService)
            }
            return INSTANCE!!
        }
    }

    override fun login(loginData: LoginData, callback: UserRepository.LoginCallback) {
        apiService.login(loginData).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.body()?.let {
                    callback.onLoggedIn(it)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onError(t)
            }

        })

        /*

        if(apiResponse.isSuccessful && apiResponse.body() != null){
            apiResponse.body()?.let {
                callback.onLoggedIn(it)
            }
        }

        callback.onError()

        return UserRepository.LoginCallback
        */
    }

    override fun getUserDetails(callback: UserRepository.GetDetailsCallback) {
        TODO("Not yet implemented")
    }

    /*
    override fun login(loginData: LoginData, callback: UserDataSource.LoginCallback) {
        dataSource.login(loginData, object : UserDataSource.LoginCallback {
            override fun onLoggedIn(token: String) {
                callback.onLoggedIn(token)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }

        })
    }

    override fun getDetails(callback: UserDataSource.GetDetailsCallback) {
        dataSource.getDetails(object : UserDataSource.GetDetailsCallback {
            override fun onDetailsLoaded(userData: UserData) {
                callback.onDetailsLoaded(userData)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }

        })
    }*/

}