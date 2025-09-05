package org.example.wardrobe.model

import kotlinx.serialization.Serializable

@Serializable
data class CombinationRequest(
    val userId: String?,
    val data: List<CategoryItem>
)
@Serializable
data class CategoryItem(
    val category: String,
    val items: List<Item>
)

