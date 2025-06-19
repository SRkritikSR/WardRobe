// AppContainer.kt
package org.example.wardrobe.di

import org.example.wardrobe.repository.ItemRepository
import org.example.wardrobe.repository.ItemRepositoryImpl

// Interface for dependency abstraction
interface AppContainer {
    val itemRepository: ItemRepository
}

// Implementation of the container
class DefaultAppContainer : AppContainer {
    override val itemRepository: ItemRepository = ItemRepositoryImpl()
}