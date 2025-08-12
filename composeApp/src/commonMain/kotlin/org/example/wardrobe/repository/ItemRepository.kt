package org.example.wardrobe.repository

import org.example.wardrobe.database.OutfitCombination
import org.example.wardrobe.model.CombinationResponse
import org.example.wardrobe.model.Item

interface ItemRepository {
    suspend fun getItems(categoryKey: String?) : List<Item>
    suspend fun getAllItems() : Map<String, List<Item>>
    suspend fun getOutfitScoresFromServer(body: String) : CombinationResponse
}