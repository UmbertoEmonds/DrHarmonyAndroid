package com.umbertoemonds.dharmonie.domain.usecases

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val POOL_SIZE = 2
    private val MAX_POOL_SIZE = 4
    private val TIMEOUT = 30

    private val handler = Handler()

    internal var threadPoolExecutor = ThreadPoolExecutor(POOL_SIZE,
                                            MAX_POOL_SIZE,
                                            TIMEOUT.toLong(),
                                            TimeUnit.SECONDS,
                                            ArrayBlockingQueue(POOL_SIZE))

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        handler.post {
            useCaseCallback.onSuccess(response)
        }
    }

    override fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable) {
        handler.post {
            useCaseCallback.onError(t)
        }
    }

    override fun <V : UseCase.ResponseValue> onSessionExpired(useCaseCallback: UseCase.UseCaseCallback<V>) {
        handler.post {
            useCaseCallback.onSessionExpired()
        }
    }
}