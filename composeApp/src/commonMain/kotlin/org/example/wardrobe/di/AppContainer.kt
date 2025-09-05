// AppContainer.kt
package org.example.wardrobe.di

import Factory
import io.ktor.client.HttpClient
import org.example.wardrobe.network.HttpClientProvider
import org.example.wardrobe.network.api.OutfitApi
import org.example.wardrobe.repository.FakeItems
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
class DefaultAppContainer(
    private val factory: Factory,
    private val httpClient: HttpClient
)
    : AppContainer {
    private val fakeItems = FakeItems()
    private val outfitApi = OutfitApi(httpClient)

    override val itemRepository: ItemRepository = ItemRepositoryImpl(
        fakeItems,
        outfitApi,
        factory.getAppDatabase().outfitDao()
    )

    override val layersListViewModel by lazy {
        LayersListViewModel(itemRepository)
    }

    override val themeViewModel by lazy {
        ThemeViewModel()
    }

    override val itemsListViewModel by lazy {
        ItemsListViewModel(itemRepository)
    }
}