package com.gmalija.core.domain.entity

import com.gmalija.core.domain.entity.Status.*

data class Result<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(SUCCESS, data, null)
        }

        fun <T> errorException(exception: Throwable): Result<T> {
            return Result(
                ERROR,
                null,
                exception.message
            )
        }

        fun <T> errorString(message: String): Result<T> {
            return Result(
                ERROR,
                null,
                message
            )
        }

        fun <T> loading(): Result<T> {
            return Result(LOADING, null, null)
        }
    }
}