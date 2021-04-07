package com.meza.domain.entity


sealed class Result<out T : Any, out k : Any> {
    data class Success<out T : Any>(val result: T) : Result<T, Nothing>()
    data class Error<out K : Any>(val error: K) :  Result<Nothing, K>()
    fun <L: Any> left(a: L) = Success(a)
    fun <K: Any> right(b: K) = Error(b)

    fun result(fnL: (T) -> Any, fnR: (k) -> Any): Any =
        when (this) {
            is Success -> fnL(result)
            is Error -> fnR(error)
        }
}