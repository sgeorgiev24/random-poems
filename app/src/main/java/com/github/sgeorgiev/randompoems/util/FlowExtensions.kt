package com.github.sgeorgiev.randompoems.util

import androidx.compose.runtime.MutableState
import com.github.sgeorgiev.randompoems.ui.home.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

inline fun <T> Flow<T>.onError(crossinline block: (Throwable) -> Unit): Flow<T> =
    catch { e ->
        block(e)
    }

fun <T> Flow<T>.loading(dataState: MutableState<DataState<T>>): Flow<T> =
    onStart {
        dataState.value = DataState.Loading()
    }
