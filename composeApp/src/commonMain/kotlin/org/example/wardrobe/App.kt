package org.example.wardrobe

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import org.example.wardrobe.di.DefaultAppContainer
import org.example.wardrobe.repository.ItemRepositoryImpl
import org.example.wardrobe.ui.screens.HomeScreen
import org.example.wardrobe.ui.screens.ItemListScreen
import org.example.wardrobe.ui.screens.SplashScreen
import org.example.wardrobe.viewmodel.ItemListViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import wardrobe.composeapp.generated.resources.Res
import wardrobe.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    val appContainer = remember { DefaultAppContainer() }
    val itemListViewModel = remember { ItemListViewModel(appContainer.itemRepository) }
    HomeScreen()
}