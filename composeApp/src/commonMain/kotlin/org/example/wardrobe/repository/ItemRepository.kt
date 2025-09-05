package org.example.wardrobe.repository

import CombinationResponse
import org.example.wardrobe.database.OutfitCombination
import org.example.wardrobe.model.CategoryItem
import org.example.wardrobe.model.Item
import org.example.wardrobe.network.ApiResult

interface ItemRepository {
    suspend fun getItems(categoryKey: String?) : List<Item>
    suspend fun getAllItems(): List<CategoryItem>
    suspend fun testRoom(): List<OutfitCombination>
    suspend fun getOutfitScoresFromServer(body: String) : ApiResult<CombinationResponse>
}