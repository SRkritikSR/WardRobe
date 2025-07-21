package org.example.wardrobe.repository

import org.example.wardrobe.model.Item

class ItemRepositoryImpl(
    private val fakeItems: FakeItems = FakeItems()
) : ItemRepository {

    override suspend fun getItems(categoryKey: String?): List<Item> {
        return when (categoryKey?.lowercase()) {
            "hats" -> fakeItems.getHats()
            "shirts" -> fakeItems.getShirts()
            "belts" -> fakeItems.getBelts()
            "bottoms" -> fakeItems.getBottoms()
            "shoes" -> fakeItems.getShoes()
            else -> emptyList()
        }
    }

    override suspend fun getAllItems(): Map<String, List<Item>> {
        return fakeItems.getAllItems()
    }
}