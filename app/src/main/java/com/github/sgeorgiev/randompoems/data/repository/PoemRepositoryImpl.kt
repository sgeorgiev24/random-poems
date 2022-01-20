package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.data.datasource.PoemApi
import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import javax.inject.Inject

class PoemRepositoryImpl @Inject constructor(
    private val poemApi: PoemApi
) : PoemRepository {

    override suspend fun getRandomPoems(): List<PoemDataModel> {
        return poemApi.getPoems()
    }
}