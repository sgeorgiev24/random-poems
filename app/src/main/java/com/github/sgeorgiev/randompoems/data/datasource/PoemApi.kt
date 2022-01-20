package com.github.sgeorgiev.randompoems.data.datasource

import com.github.sgeorgiev.randompoems.data.model.PoemDataModel
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class PoemApi @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getPoems(): List<PoemDataModel> =
        client.get("https://www.poemist.com/api/v1/randompoems")
}