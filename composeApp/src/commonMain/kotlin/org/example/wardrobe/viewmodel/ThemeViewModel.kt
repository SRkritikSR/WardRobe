package org.example.wardrobe.viewmodel

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.example.wardrobe.ui.themes.BlackTheme
import org.example.wardrobe.ui.themes.BlueTheme
import org.example.wardrobe.ui.themes.PinkTheme

class ThemeViewModel: ViewModel() {
    private val themes = listOf(BlackTheme, PinkTheme, BlueTheme )
    private val _themeIndex = mutableStateOf(0)
    val themeIndex: State<Int> get() = _themeIndex
    val currentTheme: ColorScheme
        get() = themes[_themeIndex.value]


    fun shakeTheme() {
        _themeIndex.value = (_themeIndex.value + 1) % themes.size
    }
}