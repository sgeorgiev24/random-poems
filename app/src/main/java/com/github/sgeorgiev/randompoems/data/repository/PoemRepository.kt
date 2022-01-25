package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import kotlinx.coroutines.flow.Flow

interface PoemRepository {
    fun getRandomPoems(): Flow<List<PoemDataModel>>
}