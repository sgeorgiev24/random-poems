package com.github.sgeorgiev.randompoems.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoetDataModel(
    val name: String,
    val url: String
)