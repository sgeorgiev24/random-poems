package com.github.sgeorgiev.randompoems.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import com.github.sgeorgiev.randompoems.ui.model.PoemUIModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    state: DataState<List<PoemUIModel>>,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        when (state) {
            is DataState.Error -> {
                OnError(scaffoldState, state)
            }
            is DataState.Success<List<PoemUIModel>> -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.data) { poem ->
                        Text(text = poem.title)
                    }
                }
            }
            is DataState.Idle -> {
            }
            is DataState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun OnError(
    scaffoldState: ScaffoldState,
    state: DataState.Error<List<PoemUIModel>>
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = state.msg
            )
        }
    }
}