// AppContainer.kt
package org.example.wardrobe.di

import org.example.wardrobe.repository.ItemRepository
import org.example.wardrobe.repository.ItemRepositoryImpl
import org.example.wardrobe.viewmodel.ItemsListViewModel
import org.example.wardrobe.viewmodel.LayersListViewModel
import org.example.wardrobe.viewmodel.ThemeViewModel

// Interface for dependency abstraction
interface AppContainer {
    val itemRepository: ItemRepository
    val layersListViewModel: LayersListViewModel
    val themeViewModel: ThemeViewModel
    val itemsListViewModel: ItemsListViewModel
}

// Implementation of the container
class DefaultAppContainer : AppContainer {
    override val itemRepository: ItemRepository = ItemRepositoryImpl()

    override val layersListViewModel by lazy {
        LayersListViewModel()
    }

    override val themeViewModel by lazy {
        ThemeViewModel()
    }

    override val itemsListViewModel by lazy {
        ItemsListViewModel(itemRepository)
    }
}