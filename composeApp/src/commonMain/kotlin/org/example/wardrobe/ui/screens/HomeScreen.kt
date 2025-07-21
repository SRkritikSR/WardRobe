package org.example.wardrobe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.wardrobe.viewmodel.ItemsListViewModel
import org.example.wardrobe.viewmodel.LayersListViewModel
import org.example.wardrobe.viewmodel.ThemeViewModel

@Composable
fun HomeScreen(
    themeViewModel: ThemeViewModel,
    layersListViewModel: LayersListViewModel,
    onThemeChange: () -> Unit,
    itemsListViewModel: ItemsListViewModel
) {
    HomeScreenContent(
        layersListViewModel,
        itemsListViewModel,
        onThemeChange = onThemeChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    layersListViewModel: LayersListViewModel,
    itemsListViewModel: ItemsListViewModel,
    onThemeChange: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val haptic = LocalHapticFeedback.current
    val gradient = Brush.verticalGradient(
        colors = listOf(
            colorScheme.background,
            colorScheme.surface
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        Scaffold(

            contentWindowInsets = WindowInsets(0),
            containerColor = colorScheme.surface,
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
//                            CoolText(text = "Wardrobe", onThemeChange = onThemeChange)
                            Text(
                                text = "Wardrobe",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle settings */ }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorScheme.surface,
                        scrolledContainerColor = colorScheme.surface,
                        titleContentColor = colorScheme.primary,
                        actionIconContentColor = colorScheme.primary
                    ),
                    modifier = Modifier
                        .graphicsLayer { alpha = 0.6f }
                        .blur(0.5f.dp)
                )
            },
            floatingActionButton = {
                      SphereButton(
                          modifier = Modifier
                              .size(56.dp),
                          text = "2D/3D",
                          onClick = {
                              onThemeChange()
                          }
                      )
            },
            floatingActionButtonPosition = FabPosition.Start,
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    LayersListScreen(
                        layersListViewModel = layersListViewModel,
                        itemsListViewModel = itemsListViewModel,
                    )
                }
            }
        )
    }
}

@Composable
fun CoolText(
    text: String = "Wardrobe",
    onThemeChange: () -> Unit
) {
    val haptic = LocalHapticFeedback.current

    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun SphereButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    val colorScheme = MaterialTheme.colorScheme

    // Use secondary and primary for gradient
    val gradientColors = listOf(
        colorScheme.secondary,           // lighter tone
        colorScheme.primary              // darker tone
    )

    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f))
            .border(
                width = 1.dp,                      // Outline thickness
                color = colorScheme.primary,       // Outline color
                shape = CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}