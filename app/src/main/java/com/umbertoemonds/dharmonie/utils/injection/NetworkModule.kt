package com.umbertoemonds.dharmonie.utils.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.umbertoemonds.dharmonie.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.addInterceptor(provideHeaderInterceptor())

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    private fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()

            // TODO : requestBuilder.addHeader("Authorization", token)

            it.proceed(requestBuilder.build())
        }
    }

}