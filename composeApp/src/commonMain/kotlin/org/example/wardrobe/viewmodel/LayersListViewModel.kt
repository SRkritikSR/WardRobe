package org.example.wardrobe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.example.wardrobe.model.CategoryItem
import org.example.wardrobe.model.CombinationRequest
import org.example.wardrobe.model.Layer
import org.example.wardrobe.repository.ItemRepository

class LayersListViewModel(
    private val itemRepository: ItemRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    private val _outfits : MutableStateFlow<List<CategoryItem>> = MutableStateFlow(emptyList())
    val outfits : StateFlow<List<CategoryItem>>get() = _outfits
    // List of layers
    private val _layers = mutableStateListOf(
//        Layer("Hats", 0.0f, false),
        Layer("Shirts", 0.45f, false),
//        Layer("Belts", 0.0f, false),
        Layer("Bottoms", 0.45f, false),
        Layer("Shoes", 0.1f, false)
    )
    val layers = _layers
    init {
        initOutfit()
    }

    private val _selectedItemIndices = mutableStateMapOf<String, Int>()
    val selectedItemIndices = _selectedItemIndices

    private val _selectedLayerIndex = mutableStateOf(-1)
    val selectedLayerIndex = _selectedLayerIndex

    private val _layerControlsVisibility = mutableStateMapOf<Int, Boolean>()
    val  layerControlsVisibility = _layerControlsVisibility


    val isOverlayVisible : Boolean
        get() = _selectedLayerIndex.value != -1

    val totalWeight = layers.sumOf { it.heightFraction.toDouble() }.toFloat()
    // Item counter if needed for other logic
    var itemCount = mutableStateOf(1)
        private set

    fun loadAllOutfits() = viewModelScope.launch {
        val fetchedItems = itemRepository.getAllItems()
        _outfits.value = fetchedItems
    }

    fun selectItem (layerName: String, index: Int) {
        println("The index is given by $index")
        _selectedItemIndices[layerName] = index
    }

    fun addLayer(index: Int) {
        val newItem = "Layer ${itemCount.value}"
        _layers.add(index, Layer(newItem, 0.1f))
        itemCount.value++
    }

    fun deleteLayer( index: Int ) {
        _layers.removeAt(index)
        itemCount.value--
    }

    fun disableLayer( index: Int ) {
        _layers[index].isDisabled = true
    }

    fun selectLayer(index: Int) {
        _selectedLayerIndex.value = index
    }

    fun enableControls(index: Int) {
        _layerControlsVisibility[index] = true
    }

    fun dismissControls(index: Int) {
        _layerControlsVisibility[index] = false
    }
    fun createRequest() : CombinationRequest {
        return CombinationRequest(
            userId = "",
            data = listOf()
        )
    }
    fun initOutfit() {
        _uiState.value = UiState.Loading
        val body = Json.encodeToString(createRequest())
        viewModelScope.launch {
            try {
                val data = itemRepository.getOutfitScoresFromServer(body)
                println("outfit scores fetched: $data")
                _uiState.value = UiState.Success(data)
            }
            catch (e: Exception) {
                println("Error fetching outfit scores: ${e.message}")
                _uiState.value = UiState.Error(e.message ?:"Error fetching outfit scores")
            }
        }

    }
}

