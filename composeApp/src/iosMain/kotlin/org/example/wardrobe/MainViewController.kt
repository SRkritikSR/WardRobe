package org.example.wardrobe

import androidx.compose.ui.window.ComposeUIViewController
import org.example.wardrobe.di.DefaultAppContainer
import org.example.wardrobe.network.HttpClientProvider

fun MainViewController() = ComposeUIViewController {
    val httpClient = HttpClientProvider.client
    val appContainer = DefaultAppContainer(
        httpClient
    )
    App(appContainer)
}