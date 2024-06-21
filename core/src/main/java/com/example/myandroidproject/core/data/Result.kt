package com.example.myandroidproject.core.data

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val error: String) : Result<Nothing>()
}