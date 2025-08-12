package org.example.wardrobe.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import org.example.wardrobe.model.Item
import org.example.wardrobe.viewmodel.ItemsListViewModel
import org.jetbrains.compose.resources.painterResource
import wardrobe.composeapp.generated.resources.Res
import wardrobe.composeapp.generated.resources.ic_belt
import wardrobe.composeapp.generated.resources.ic_bottom
import wardrobe.composeapp.generated.resources.ic_hat
import wardrobe.composeapp.generated.resources.ic_shirt
import wardrobe.composeapp.generated.resources.ic_shoe

@Composable
    fun ItemListScreen(
    viewModel: ItemsListViewModel,
    categoryKey: String,
    categoryOutfits: List<Item>,
    selectedIndex: Int,
    selectItemListener: (String, Int) -> Unit,
    enableSmoothScroll: Boolean,
    isOverlay: Boolean,
    onDismissLayer: () -> Unit = {}
    ) {
        val localImages = listOf(
            Res.drawable.ic_hat,
            Res.drawable.ic_shirt,
            Res.drawable.ic_belt,
            Res.drawable.ic_bottom,
            Res.drawable.ic_shoe
        )
        val coroutineScope = rememberCoroutineScope()
        val haptic = LocalHapticFeedback.current
        val actualItems = categoryOutfits
        val fakeSize = Int.MAX_VALUE
        val centerIndex = fakeSize / 2
        val listState = rememberLazyListState(initialFirstVisibleItemIndex = centerIndex)
        val snapBehavior = rememberSnapFlingBehavior(lazyListState = listState)
        var imageState by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

//        LaunchedEffect(selectedIndex) {
//            if (selectedIndex != null) {
//                listState.animateScrollToItem(selectedIndex)
//            }
//        }
        print("${isOverlay} ")
        LazyRow(
            state = listState,
            flingBehavior = snapBehavior,
            userScrollEnabled = enableSmoothScroll,
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(horizontal = 0.dp),
                ) {
                    items(localImages.size) { index ->
                        val actualIndex = index
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .then(if (isOverlay) Modifier.clickable(onClick = {
                                    print("Overlay is being dismissed")
                                    onDismissLayer()
                                    selectItemListener(categoryKey,index)

                                }) else Modifier),
                            contentAlignment = Alignment.Center
                        )

                        {
                            Text(
                                modifier = Modifier.fillParentMaxWidth(),
                                text ="Item $index")
//                            Image(
//                                painter = painterResource(localImages[index]),
//                                contentDescription = ""
//                            )
//                            AsyncImage(
//                                model = categoryOutfits[index].url,
//                                contentDescription = "cloth",
//                                modifier = Modifier
//                                    .fillParentMaxWidth() // This ensures it takes full width of parent
//                                    .padding(8.dp),
//                                contentScale = ContentScale.Crop,
//                                onState = {state ->
//                                    imageState = state
//                                }
//                            )

                        }
//                        when (imageState) {
//                            is AsyncImagePainter.State.Loading -> {
//                                CircularProgressIndicator(
//
//                                    modifier = Modifier
//                                        .size(36.dp),
//                                    strokeWidth = 3.dp
//                                )
//                            }
//
//                            is AsyncImagePainter.State.Error -> {
//                                print("Image loading failed, index:$index")
//                                Icon(
//                                    imageVector = Icons.Default.BrokenImage,
//                                    contentDescription = "Image Load Failed",
//                                    tint = Color.Red,
//                                    modifier = Modifier.size(36.dp)
//                                )
//                            }
//
//                            else -> {
//                                // Success or empty — nothing to overlay
//                            }
//                        }
                    }
                }



    }