package com.hazem.corelayer.model

sealed class ErrorEntity {
    object NoConnection : ErrorEntity()
    object Unknown : ErrorEntity()

    data class ApiError(val code: Int, val message: List<String>) : ErrorEntity()
    data class InternalError(  val message: String) : ErrorEntity()

    object None : ErrorEntity()
}

