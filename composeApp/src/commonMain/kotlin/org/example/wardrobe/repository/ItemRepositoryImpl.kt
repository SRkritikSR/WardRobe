package org.example.wardrobe.repository

import org.example.wardrobe.model.Item

class ItemRepositoryImpl : ItemRepository {
    override suspend fun getItems(): List<Item> {
        return listOf(
            Item(name ="Shirt"),
            Item(name = "Pants"),
            Item(name = "Shoes")
        )
    }
}