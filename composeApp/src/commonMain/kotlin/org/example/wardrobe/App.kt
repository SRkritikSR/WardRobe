package org.example.wardrobe

import androidx.compose.runtime.*
import org.example.wardrobe.di.DefaultAppContainer
import org.example.wardrobe.ui.screens.HomeScreen
import org.example.wardrobe.ui.themes.WardrobeTheme

@Composable
fun App() {
    val appContainer = remember { DefaultAppContainer() }

//    PlatformWebView(
//        glbUrl = "http://127.0.0.1:8000/models/sample_mannequin.glb",
//        modifier = Modifier
//            .fillMaxSize()
//    )
    WardrobeTheme(colorScheme = appContainer.themeViewModel.currentTheme) {
        HomeScreen(
            themeViewModel = appContainer.themeViewModel,
            layersListViewModel = appContainer.layersListViewModel,
            itemsListViewModel = appContainer.itemsListViewModel,
            onThemeChange = { appContainer.themeViewModel.shakeTheme() }
        )
    }
}