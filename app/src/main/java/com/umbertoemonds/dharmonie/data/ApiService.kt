package com.umbertoemonds.dharmonie.data

import com.umbertoemonds.dharmonie.data.models.Accord
import com.umbertoemonds.dharmonie.data.models.Grille
import com.umbertoemonds.dharmonie.domain.models.LoginData
import com.umbertoemonds.dharmonie.data.models.UserData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("/login")
    fun login(@Body loginData: LoginData): Call<String>

    @GET("/user")
    fun getUserDetails(): Call<UserData>

    @GET("/grilles")
    fun getGrilles(@Header("Authorization") token: String): Call<List<Grille>>

    @GET("/grilles/{id}")
    fun getAccords(@Header("Authorization") token: String, @Path("id") idGrille: Int) : Call<List<Accord>>

}