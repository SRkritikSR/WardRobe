package org.example.wardrobe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.example.wardrobe.repository.ItemRepository
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.wardrobe.viewmodel.ItemListViewModel

@Composable
    fun ItemListScreen(viewModel: ItemListViewModel) {
        val items by viewModel.items.collectAsState()
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 56.dp)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
        )   {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.name,
                        fontSize = 24.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }