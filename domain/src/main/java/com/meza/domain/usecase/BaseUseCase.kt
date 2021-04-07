package com.meza.domain.usecase

import com.meza.domain.entity.Failure
import com.meza.domain.entity.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * By convention each [BaseUseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type, Failure>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Type, Failure>) -> Unit = {}
    ) {
        /*
         * Credits to Paulo.
         * https://proandroiddev.com/i-exchanged-rxjava-for-coroutines-in-my-android-application-why-you-probably-should-do-the-same-5526dfb38d0e#cf27
         *
         * Basically. All exceptions that could occur while invoking the service will be handled on EndPointImpl because
         * the response.call function is wrapped inside a try catch block. If something else occur outside of that block (like Data -> Domain mappers)
         * those exceptions will be caught here. On the Launch scope.
         */

        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }

}