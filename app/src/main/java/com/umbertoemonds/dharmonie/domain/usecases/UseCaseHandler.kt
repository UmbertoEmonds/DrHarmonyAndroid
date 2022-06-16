package com.umbertoemonds.dharmonie.domain.usecases

class UseCaseHandler (private val useCaseScheduler: UseCaseScheduler) {

    companion object {
        private var INSTANCE: UseCaseHandler? = null

        fun getInstance(): UseCaseHandler {
            if (INSTANCE == null){
                INSTANCE = UseCaseHandler(UseCaseThreadPoolScheduler())
            }
            return INSTANCE!!
        }
    }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(useCase: UseCase<T, R>, value: T, callback: UseCase.UseCaseCallback<R>){
        useCase.requestValues = value
        useCase.useCaseCallback = UiCallbackWrapper(callback, this)

        useCaseScheduler.execute {
            useCase.run()
        }
    }

    private fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>){
        useCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable) {
            useCaseScheduler.onError(useCaseCallback, t)
    }

    private fun <V : UseCase.ResponseValue> notifySessionExpired(useCaseCallback: UseCase.UseCaseCallback<V>){
        useCaseScheduler.onSessionExpired(useCaseCallback)
    }

    private class UiCallbackWrapper<V : UseCase.ResponseValue>(
        private val callback: UseCase.UseCaseCallback<V>,
        private val useCaseHandler: UseCaseHandler) : UseCase.UseCaseCallback<V> {

        override fun onSuccess(response: V) {
            useCaseHandler.notifyResponse(response, callback)
        }

        override fun onError(t: Throwable) {
            useCaseHandler.notifyError(callback, t)
        }

        override fun onSessionExpired() {
            useCaseHandler.notifySessionExpired(callback)
        }
    }

}