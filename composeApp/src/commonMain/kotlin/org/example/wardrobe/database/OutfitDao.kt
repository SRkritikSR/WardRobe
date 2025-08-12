package org.example.wardrobe.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface OutfitDao {
    @Query("SELECT * FROM outfit_combinations")
    suspend fun getAll(): List<OutfitCombination>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(combinations: List<OutfitCombination>)

    @Query("DELETE FROM outfit_combinations")
    suspend fun clearAll()

}