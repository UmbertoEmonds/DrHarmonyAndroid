package com.umbertoemonds.dharmonie.utils.injection

import com.umbertoemonds.dharmonie.data.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    fun bindApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}