package com.github.sgeorgiev.randompoems.di

import android.util.Log
import com.github.sgeorgiev.randompoems.data.datasource.PoemApi
import com.github.sgeorgiev.randompoems.data.repository.PoemRepository
import com.github.sgeorgiev.randompoems.data.repository.PoemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient =
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                })
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Network module", message)
                    }

                }
                level = LogLevel.ALL
            }
        }

    @Provides
    fun providePoemRepository(poemApi: PoemApi): PoemRepository = PoemRepositoryImpl(poemApi)
}