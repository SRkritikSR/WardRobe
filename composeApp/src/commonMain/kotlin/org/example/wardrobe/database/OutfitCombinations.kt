package org.example.wardrobe.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outfit_combinations")
data class OutfitCombination(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val shirtIndex: Int,
    val pantIndex: Int,
    val shoeIndex: Int,
    val avgEmbeddings: Double,
)




