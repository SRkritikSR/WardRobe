package org.example.wardrobe.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.wardrobe.model.Item
import org.example.wardrobe.repository.ItemRepository

class ItemsListViewModel(
    private val repository: ItemRepository
): ViewModel() {

    // use view model scope to fetch the data
    private val _outfits : MutableStateFlow<Map<String, List<Item>>> = MutableStateFlow(emptyMap())
    val outfits : StateFlow<Map<String, List<Item>>>get() = _outfits

    var showItemSelection by mutableStateOf(false)
        private set

    fun lockItem(index: Int) {

    }

    fun deleteItem(index: Int) {


    }

    fun loadAllOutfits() = viewModelScope.launch {
        val fetchedItems = repository.getAllItems()
        _outfits.value = fetchedItems
    }

    fun toggleItemSelections(index: Int)  {
            showItemSelection = true
    }

    init {
        loadAllOutfits()
    }
}