package org.example.wardrobe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.wardrobe.model.Item
import org.example.wardrobe.repository.ItemRepository

class ItemListViewModel(
    private val repository: ItemRepository
): ViewModel() {

    // use view model scope to fetch the data
    private val _items :MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    val items : StateFlow<List<Item>> get() = _items

    fun fetchItems() = viewModelScope.launch {
        println("ItemListViewModel Fetching items")
        val fetchedItems = repository.getItems()
        println("fetched from repo $fetchedItems")
        _items.value = fetchedItems
    }

    init {
        fetchItems()
    }
}