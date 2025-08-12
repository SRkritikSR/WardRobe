// NetworkModule.kt or HttpClientProvider.kt
package org.example.wardrobe.network

import io.ktor.client.*

expect object HttpClientProvider {
    val client: HttpClient
}