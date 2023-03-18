package com.example.myapplication.utils

open class UiState(
    open val isLoading: Boolean = false,
    val error: Error? = null
) {
    override fun toString(): String {
        return "loading: $isLoading " +
                "\nerror: $error"
    }

    sealed class Error {
         object NetworkError : Error()
         object GeneralError : Error()
    }
}
