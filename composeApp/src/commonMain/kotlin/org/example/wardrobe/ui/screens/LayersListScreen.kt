package org.example.wardrobe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import org.example.wardrobe.viewmodel.ItemsListViewModel
import org.example.wardrobe.viewmodel.LayersListViewModel

@Composable
fun LayersListScreen(
    layersListViewModel: LayersListViewModel,
    itemsListViewModel: ItemsListViewModel
) {
    var itemCount = layersListViewModel.itemCount
    val layers = layersListViewModel.layers
    val totalWeight = layersListViewModel.totalWeight
    val isOverlayVisible = layersListViewModel.isOverlayVisible
    val selectionOverlayIndex = layersListViewModel.selectedLayerIndex
    val layerControlsVisibilityMap = layersListViewModel.layerControlsVisibility
        val enabledItems = layers.filter { !it.isDisabled }

    val haptic = LocalHapticFeedback.current

    Surface(
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ){
            Column(
                modifier = Modifier
                    .then(
                        if (selectionOverlayIndex.value != -1)
                            Modifier
                                .blur(20.dp)
                        else Modifier
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until layers.size) {
                    Box (
                        modifier = Modifier
                            .weight( layers[i].heightFraction / totalWeight )
                            .combinedClickable (
                                onClick = {
                                    haptic.performHapticFeedback(HapticFeedbackType.Confirm)
                                    layersListViewModel.selectLayer(i) },
                                onDoubleClick= {},
                                onLongClick = {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    layersListViewModel.enableControls(index = i) }
                            )


                    ){

                        Box(
                            modifier = Modifier
                                .then(if(layerControlsVisibilityMap[i] == true) Modifier.blur(20.dp) else Modifier)
                        )
                        {
                            ItemListScreen(
                                layersListViewModel,
                                viewModel = itemsListViewModel,
                                categoryKey = layers[i].name,
                                enableSmoothScroll = false,
                                isOverlay = isOverlayVisible
                            )
                        }



                        if (layerControlsVisibilityMap[i] == true) {
                            // Show controls
                            LayerControlsButtonGroup(
                                onLayerLocked = {
                                    layersListViewModel.dismissControls(i)
                                },
                                onLayerRemoved = {
                                    layersListViewModel.dismissControls(i)
                                },
                                onLayerAdded = {
                                    layersListViewModel.dismissControls(i)
                                }
                            )
                        }


                    }


                    // Only visible in skeleton mode
//                    IconButton(
//                        onClick = {
//                            layersListViewModel.addLayer(index = i)
//                        },
//                        modifier = Modifier
//                            .size(48.dp) // Size = makes it circular
//
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AddCircleOutline,
//                            contentDescription = "Add",
//                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
//                            modifier = Modifier
//                                .size(24.dp) // Adjust icon size if needed
//
//
//                        )
//                    }

                }

            }

            if (isOverlayVisible) {
                Box (
                    modifier = Modifier
                        .fillMaxSize(0.75f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
//                        .clickable(onClick = {selectionOverlayIndex = -1}),
                    ){
                    ItemListScreen(
                        layersListViewModel,
                        viewModel = itemsListViewModel,
                        categoryKey = layers[selectionOverlayIndex.value].name,
                        enableSmoothScroll = true,
                        isOverlay = isOverlayVisible,
                        onDismissLayer = {
                            layersListViewModel.selectLayer(-1)
                        }
                    )



                }
            }
        }

    }
}

@Composable
fun BoxScope.LayerControlsButtonGroup (
    onLayerLocked: () -> Unit,
    onLayerAdded: () -> Unit,
    onLayerRemoved: () -> Unit
) {
    Box(
    modifier = Modifier
        .align(Alignment.Center) // or use Alignment.BottomCenter if preferred
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White.copy(alpha = 0.1f))
        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
        .padding(8.dp)

    )
    {
        Row(
            modifier = Modifier

        )
        {
            // 1. Add Layer
            IconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = onLayerAdded
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add Layer"
                )
            }

            // 2. Lock Layer
            IconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = onLayerLocked
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.LockOpen,
                    contentDescription = "Lock Layer"
                )
            }

            // 3. Remove Layer
            IconButton(
                modifier = Modifier
                    .size(48.dp) ,
                onClick = onLayerRemoved
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Remove Layer"
                )
            }
        }
    }

}
