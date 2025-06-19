package org.example.wardrobe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    val fabModifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE1CBD6),
            Color(0xFFE1CBD6),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        Scaffold(
            contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0),
            containerColor = Color.Transparent, // transparent to blend background
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Wardrobe",
                                color = Color(0xFF6E4466), // Hex: #6E4466,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Settings click */ }) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color(0xFFC397AD)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFE1CBD6), // fully transparent
                        scrolledContainerColor = Color.Transparent,
                        titleContentColor = Color(0xFF7A3F53),
                        actionIconContentColor = Color(0xFFC397AD)
                    ),
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = 0.4f
                        }
                        .blur(1.dp) // optional for subtle glass effect
                )
            },
            floatingActionButton = {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .graphicsLayer {
                            alpha = 0.4f
                            shape = CircleShape
                            clip = true
                        }
                        .blur(1.dp)
                ) {
                    FloatingActionButton(
                        onClick = { /* FAB click */ },
                        containerColor = Color(0xFFC397AD),
                        contentColor = Color.Black,
                        elevation = FloatingActionButtonDefaults.elevation(10.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    // Main content
                }
            }
        )
    }
}