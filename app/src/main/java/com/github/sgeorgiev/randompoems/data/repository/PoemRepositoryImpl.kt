package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.data.datasource.PoemApi
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import com.github.sgeorgiev.randompoems.ui.model.PoemUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemApi: PoemApi
) : PoemRepository {

    override fun getRandomPoems() = flow {
        val response = poemApi.getPoems()
        val poemUIModel = response.map {
            PoemUIModel(it.title, it.content)
        }
        emit(poemUIModel)
    }.flowOn(Dispatchers.IO)
}