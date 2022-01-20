package com.github.sgeorgiev.randompoems.data.repository

import com.github.sgeorgiev.randompoems.data.model.PoemDataModel

interface PoemRepository {
    suspend fun getRandomPoems(): List<PoemDataModel>
}