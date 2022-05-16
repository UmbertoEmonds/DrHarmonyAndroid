package com.umbertoemonds.dharmonie.utils.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.umbertoemonds.dharmonie.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.addInterceptor(provideHeaderInterceptor())
        return okHttpClientBuilder.build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

}