package com.umbertoemonds.dharmonie.domain.usecases

import com.umbertoemonds.dharmonie.data.models.Grille
import com.umbertoemonds.dharmonie.domain.repositories.GrilleRepository

class GrilleUseCase(private val grilleRepository: GrilleRepository) : UseCase<GrilleUseCase.RequestValues, GrilleUseCase.ResponseValue>() {

    class RequestValues(val token: String) : UseCase.RequestValues
    class ResponseValue(val grilles: List<Grille>) : UseCase.ResponseValue

    override fun executeUseCase(requestValues: RequestValues?) {
        requestValues?.token?.let {

            grilleRepository.getGrilles(it, object : GrilleRepository.GrilleCallback {

                override fun onGrillesLoaded(grilles: List<Grille>) {
                    val responseValue = ResponseValue(grilles)
                    useCaseCallback?.onSuccess(responseValue)
                }

                override fun onSessionExpired() {
                    useCaseCallback?.onSessionExpired()
                }

                override fun onError(t: Throwable) {
                    useCaseCallback?.onError(t)
                }

            })

        }

    }

}