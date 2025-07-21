package org.example.wardrobe.ui.themes

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val AccentColor = Color(0xFFC397AD) // #FFC397AD as secondary

val PinkTheme = lightColorScheme(
    background = Color(0xFFE1CBD6),
    surface = Color(0xFFE1CBD6),
    primary = AccentColor,
    onPrimary = Color.Black,
    secondary = AccentColor,
    onSecondary = Color.Black,
    onBackground = AccentColor,
    onSurface = AccentColor
)

val BlueTheme = lightColorScheme(
    background = Color(0xFFD6EAF8),
    surface = Color(0xFFD6EAF8),
    primary = Color(0xFF7BAEDC),
    onPrimary = Color.White,
    secondary = AccentColor,
    onSecondary = Color.Black,
    onBackground = AccentColor,
    onSurface = AccentColor
)

val BlackTheme = lightColorScheme(
    background = Color.Black,
    surface = Color.Black,
    primary = AccentColor,
    onPrimary = Color.Black,
    secondary = AccentColor,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)