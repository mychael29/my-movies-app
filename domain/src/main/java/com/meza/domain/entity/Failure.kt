package com.meza.domain.entity

sealed class Failure {
    /** When service return 401 or 403 this will force the client to log out of the app.*/
    data class UnauthorizedOrForbidden(val userMessage: String) : Failure()

    /** Weird and strange error that we don´t know the cause.*/
    object None : Failure()

    /** When suddenly the connection is lost.*/
    object NetworkConnectionLostSuddenly : Failure()

    object SSLError: Failure()

    /** When service is taking to long on return the response.*/
    object TimeOut: Failure()

    /** This class is for feature specific failures.*/
    data class ServiceUncaughtFailure(val uncaughtFailureMessage: String) : Failure()

    /** This class is for feature specific SERVICE ERROR BODY RESPONSE.*/
    data class ServerBodyError(val message: String) : Failure()

    data class ServerError(val message: String) : Failure()

    /** This class is for feature specific DATA -> DOMAIN MAPPERS exceptions.*/
    data class DataToDomainMapperFailure(val mapperException: String?) : Failure()
}