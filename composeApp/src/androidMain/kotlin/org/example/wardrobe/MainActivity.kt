package org.example.wardrobe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.wardrobe.di.DefaultAppContainer
import org.example.wardrobe.network.HttpClientProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val httpClient = HttpClientProvider.client
        val appContainer = DefaultAppContainer(
            httpClient
        )
        setContent {
            App(appContainer)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}