package com.example.domain.utils

sealed class DomainResponse<T>(val data: T? = null) {
    class Success<T>(data: T?) : DomainResponse<T>(data)

    class Error<T> : DomainResponse<T>()
}