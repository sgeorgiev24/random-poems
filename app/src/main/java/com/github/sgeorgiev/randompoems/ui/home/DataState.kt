package com.github.sgeorgiev.randompoems.ui.home

sealed class DataState<T> {
    class Loading<T> : DataState<T>()
    class Idle<T> : DataState<T>()
    data class Success<T>(val data: T): DataState<T>()
    data class Error<T>(val msg: String): DataState<T>()
}