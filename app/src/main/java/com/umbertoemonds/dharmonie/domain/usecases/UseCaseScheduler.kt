package com.umbertoemonds.dharmonie.domain.usecases

interface UseCaseScheduler {
    fun execute(runnable: Runnable)
    fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>)
    fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable)
    fun <V : UseCase.ResponseValue> onSessionExpired(useCaseCallback: UseCase.UseCaseCallback<V>)
}