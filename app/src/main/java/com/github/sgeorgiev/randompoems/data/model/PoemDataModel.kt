package com.github.sgeorgiev.randompoems.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PoemDataModel(
    val title: String,
    val content: String,
    val url: String,
    val poet: PoetDataModel
)
