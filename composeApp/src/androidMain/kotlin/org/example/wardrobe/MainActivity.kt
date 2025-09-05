package org.example.wardrobe

import Factory
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
        val factory = Factory(application)
        val httpClient = HttpClientProvider.client
        val appContainer = DefaultAppContainer(
            factory,
            httpClient
        )
        setContent {
            App(appContainer)
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App(appContainer = DefaultAppContainer(Factory())
//}