package org.example.wardrobe

import Factory
import androidx.compose.ui.window.ComposeUIViewController
import org.example.wardrobe.di.DefaultAppContainer
import org.example.wardrobe.network.HttpClientProvider

fun MainViewController() = ComposeUIViewController {
    val factory = Factory()
    val httpClient = HttpClientProvider.client
    val appContainer = DefaultAppContainer(
        factory,
        httpClient
    )
    App(appContainer)
}