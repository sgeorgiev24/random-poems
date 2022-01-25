package com.github.sgeorgiev.randompoems.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import com.github.sgeorgiev.randompoems.data.repository.PoemRepository
import com.github.sgeorgiev.randompoems.util.loading
import com.github.sgeorgiev.randompoems.util.onError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val poemRepository: PoemRepository
) : ViewModel() {

    private val _state: MutableState<DataState<List<PoemDataModel>>> =
        mutableStateOf(DataState.Idle())
    val state: State<DataState<List<PoemDataModel>>> = _state

    init {
        poemRepository.getRandomPoems()
            .loading(_state)
            .onEach {
                _state.value = DataState.Success(it)
            }
            .onError {
                _state.value = DataState.Error("Cannot get poems...")
            }
            .launchIn(viewModelScope)
    }
}