package org.example.wardrobe.repository

import org.example.wardrobe.model.Item

interface ItemRepository {
    suspend fun getItems(): List<Item>
}