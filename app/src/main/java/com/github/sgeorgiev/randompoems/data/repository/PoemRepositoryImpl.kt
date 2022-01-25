package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.data.datasource.PoemApi
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemApi: PoemApi
) : PoemRepository {

    override fun getRandomPoems() = flow {
        emit(poemApi.getPoems())
    }.flowOn(Dispatchers.IO)
}