package org.example.wardrobe.model

data class Layer(
    val name: String,
    val heightFraction: Float,
    var isDisabled : Boolean = false
)
