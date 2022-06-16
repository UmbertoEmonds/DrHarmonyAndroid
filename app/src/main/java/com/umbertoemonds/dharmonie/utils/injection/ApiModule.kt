package com.umbertoemonds.dharmonie.utils.injection

import android.content.Context
import com.umbertoemonds.dharmonie.data.ApiService
import retrofit2.Retrofit

object ApiModule {

    fun create(): ApiService {
        val client = NetworkModule.provideOkHttpClient()
        val retrofit = NetworkModule.provideRetrofit(client)

        // TODO integrer la ligne suivante dans une factory pour appeler un autre service si besoin
        return retrofit.create(ApiService::class.java)
    }

}