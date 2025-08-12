package org.example.wardrobe.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual object HttpClientProvider {
    actual val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(HttpTimeout) {
                requestTimeoutMillis = 240_000  // 60 seconds
                connectTimeoutMillis = 30_000
                socketTimeoutMillis = 240_000
            }
            install(ContentNegotiation) {
                json(
                    Json{
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
}