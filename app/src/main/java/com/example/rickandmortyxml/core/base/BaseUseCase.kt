package com.example.rickandmortyxml.core.base

import kotlinx.coroutines.*

abstract class BaseUseCase<in Params, out T> {

    abstract suspend fun action(params: Params): T

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<T>) -> Unit = {}
    ) {
        scope.launch {
            val result = withContext(dispatcher) {
                runCatching { action(params) }
            }
            onResult(result)
        }

    }

}