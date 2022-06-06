package com.umbertoemonds.dharmonie.data

import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    fun login(@Body loginData: LoginData): Call<String>

    @GET("/user")
    fun getUserDetails(): Call<UserData>

}