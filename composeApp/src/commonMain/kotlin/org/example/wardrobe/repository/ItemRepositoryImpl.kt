package org.example.wardrobe.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import org.example.wardrobe.database.OutfitCombination
import org.example.wardrobe.model.CombinationResponse
import org.example.wardrobe.model.Item
import org.example.wardrobe.network.HttpClientProvider.client

class ItemRepositoryImpl(
    private val fakeItems: FakeItems,
    private val httpClient: HttpClient
) : ItemRepository {

    override suspend fun getItems(categoryKey: String?): List<Item> {
        return when (categoryKey?.lowercase()) {
            "hats" -> fakeItems.getHats()
            "shirts" -> fakeItems.getShirts()
            "belts" -> fakeItems.getBelts()
            "bottoms" -> fakeItems.getBottoms()
            "shoes" -> fakeItems.getShoes()
            else -> emptyList()
        }
    }

    override suspend fun getAllItems(): Map<String, List<Item>> {
        return fakeItems.getAllItems()
    }
    override suspend fun getOutfitScoresFromServer(body: String): CombinationResponse {
        val response: HttpResponse =
            client.post("https://a66defd93318.ngrok-free.app/init_outfit") {
            setBody(body)
        }
        if (response.status.value == 400) {
            return CombinationResponse(
                response.body()
            )
        }
        return CombinationResponse(response.body())
    }
}