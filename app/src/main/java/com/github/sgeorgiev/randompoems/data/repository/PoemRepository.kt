package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.ui.model.PoemUIModel
import kotlinx.coroutines.flow.Flow

interface PoemRepository {
    fun getRandomPoems(): Flow<List<PoemUIModel>>
}