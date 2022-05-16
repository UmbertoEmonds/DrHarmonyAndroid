package com.umbertoemonds.dharmonie.data

import com.umbertoemonds.dharmonie.data.bodies.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    suspend fun login(@Body loginData: LoginData): Single<String>

    @GET("/user")
    suspend fun getUserDetails(): Single<UserData>

}