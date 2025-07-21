package org.example.wardrobe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.example.wardrobe.model.Layer

class LayersListViewModel(): ViewModel() {
    // List of layers
    private val _layers = mutableStateListOf(
//        Layer("Hats", 0.0f, false),
        Layer("Shirts", 0.45f, false),
//        Layer("Belts", 0.0f, false),
        Layer("Bottoms", 0.45f, false),
        Layer("Shoes", 0.1f, false)
    )
    val layers = _layers

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

}