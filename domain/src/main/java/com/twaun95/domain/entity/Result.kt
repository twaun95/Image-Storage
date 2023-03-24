package com.twaun95.domain.entity

import java.lang.Exception

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Fail(val exception: Exception) : Result<Nothing>()
}