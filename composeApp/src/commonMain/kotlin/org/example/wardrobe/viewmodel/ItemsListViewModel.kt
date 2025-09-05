package org.example.wardrobe.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.wardrobe.model.CategoryItem
import org.example.wardrobe.model.Item
import org.example.wardrobe.repository.ItemRepository

class ItemsListViewModel(
    private val repository: ItemRepository
): ViewModel() {


    var showItemSelection by mutableStateOf(false)
        private set

    fun lockItem(index: Int) {

    }

    fun deleteItem(index: Int) {


    }

    fun toggleItemSelections(index: Int)  {
            showItemSelection = true
    }

}