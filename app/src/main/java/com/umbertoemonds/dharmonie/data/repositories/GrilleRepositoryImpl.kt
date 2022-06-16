package com.umbertoemonds.dharmonie.data.repositories

import com.umbertoemonds.dharmonie.data.ApiService
import com.umbertoemonds.dharmonie.data.models.Accord
import com.umbertoemonds.dharmonie.data.models.Grille
import com.umbertoemonds.dharmonie.domain.repositories.GrilleRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GrilleRepositoryImpl constructor(val apiService: ApiService) : GrilleRepository {

    companion object {
        private var INSTANCE: GrilleRepositoryImpl? = null

        fun getInstance(apiService: ApiService) : GrilleRepositoryImpl {
            if(INSTANCE == null){
                INSTANCE = GrilleRepositoryImpl(apiService)
            }
            return INSTANCE!!
        }
    }

    override fun getGrilles(userToken: String, callback: GrilleRepository.GrilleCallback) {
        apiService.getGrilles(userToken).enqueue(object : Callback<List<Grille>> {
            override fun onResponse(call: Call<List<Grille>>, response: Response<List<Grille>>) {
                val code = response.code()

                if(code == 403 || code == 401){
                    callback.onSessionExpired()
                }

                response.body()?.let {
                    callback.onGrillesLoaded(it)
                }
            }

            override fun onFailure(call: Call<List<Grille>>, t: Throwable) {
                callback.onError(t)
            }

        })
    }

    override fun getAccords(grilleId: Int, userToken: String, callback: GrilleRepository.AccordsCallback) {
        apiService.getAccords(userToken, grilleId).enqueue(object : Callback<List<Accord>> {
            override fun onResponse(call: Call<List<Accord>>, response: Response<List<Accord>>) {
                response.body()?.let {
                    callback.onAccordLoaded(it)
                }
            }

            override fun onFailure(call: Call<List<Accord>>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

}