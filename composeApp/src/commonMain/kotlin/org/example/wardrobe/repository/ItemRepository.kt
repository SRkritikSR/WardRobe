package org.example.wardrobe.repository

import org.example.wardrobe.model.Item

interface ItemRepository {
    suspend fun getItems(categoryKey: String?): List<Item>
    suspend fun getAllItems(): Map<String, List<Item>>
}