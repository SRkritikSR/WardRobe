package org.example.wardrobe.repository

import CombinationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException
import org.example.wardrobe.config.Constants
import org.example.wardrobe.database.OutfitCombination
import org.example.wardrobe.database.OutfitDao
import org.example.wardrobe.model.CategoryItem
import org.example.wardrobe.model.Item
import org.example.wardrobe.network.ApiResult
import org.example.wardrobe.network.api.OutfitApi

class ItemRepositoryImpl(
    private val fakeItems: FakeItems,
    private val outfitApi: OutfitApi,
    private val outfitDao: OutfitDao
) : ItemRepository {

    override suspend fun getItems(categoryKey: String?): List<Item> {
        return when (categoryKey?.lowercase()) {
//            "hats" -> fakeItems.getHats()
            "shirts" -> fakeItems.getShirts()
//            "belts" -> fakeItems.getBelts()
            "bottoms" -> fakeItems.getBottoms()
            "shoes" -> fakeItems.getShoes()
            else -> emptyList()
        }
    }

    override suspend fun getAllItems(): List<CategoryItem> {
        // get all images loaded
        return fakeItems.getAllItems()
    }

    override suspend fun testRoom(): List<OutfitCombination> {
        outfitDao.clearAll()
        outfitDao.insertAll((listOf(OutfitCombination(shirtIndex = 0, pantIndex = 0, shoeIndex = 0, avgEmbeddings = 0.85))))
        val result = outfitDao.getAll()
        print("Data en database: $result")
        return result

    }


    override suspend fun getOutfitScoresFromServer(body: String): ApiResult<CombinationResponse> {
        return try {
            ApiResult.Success(outfitApi.getOutfitScores(body))
        } catch (e: IOException) {
            print("API Result $e")
            ApiResult.NetworkError
        } catch (e: Exception) {
            print("API Result $e")
            ApiResult.Error(e.message ?: "Unknown error")
        }
    }
}