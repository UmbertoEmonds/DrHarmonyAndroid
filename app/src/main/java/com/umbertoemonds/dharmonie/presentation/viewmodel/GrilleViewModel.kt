package com.umbertoemonds.dharmonie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.umbertoemonds.dharmonie.domain.repositories.GrilleRepository
import com.umbertoemonds.dharmonie.domain.usecases.GrilleUseCase
import com.umbertoemonds.dharmonie.domain.usecases.UseCase
import com.umbertoemonds.dharmonie.domain.usecases.UseCaseHandler

class GrilleViewModel (private val useCaseHandler: UseCaseHandler, private val grilleUseCase: GrilleUseCase) : ViewModel() {

    fun getGrilles(token: String, callback: GrilleRepository.GrilleCallback) {
        val requestValue = GrilleUseCase.RequestValues(token)

        useCaseHandler.execute(grilleUseCase, requestValue, object : UseCase.UseCaseCallback<GrilleUseCase.ResponseValue>{
            override fun onSuccess(response: GrilleUseCase.ResponseValue) {
                callback.onGrillesLoaded(response.grilles)
            }

            override fun onError(t: Throwable) {
                callback.onError(t)
            }

            override fun onSessionExpired() {
                callback.onSessionExpired()
            }

        })
    }

}