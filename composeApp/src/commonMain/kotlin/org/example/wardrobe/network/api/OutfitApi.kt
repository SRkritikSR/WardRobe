package org.example.wardrobe.network.api

import CombinationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.wardrobe.config.Constants
import org.example.wardrobe.config.Constants.BASE_URL
import org.example.wardrobe.model.CategoryItem
import org.example.wardrobe.model.CombinationRequest
import org.example.wardrobe.model.Item

class OutfitApi(private val httpClient: HttpClient) {
    suspend fun getOutfitScores(body: String) : CombinationResponse {
        val fakeRequest = CombinationRequest(
            userId = Constants.TEST_USER_ID,
            data = listOf(
                CategoryItem("tops", listOf(Item(url = "shirt_white"))),
                CategoryItem("bottoms", listOf(Item(url = "shirt_white"))),
                CategoryItem("shoewears", listOf(Item(url = "shirt_white")))
            )
        )
        val result = httpClient.post("$BASE_URL${Constants.Endpoints.INIT_OUTFITS_ENDPOINT}"){
            contentType(ContentType.Application.Json)
            setBody(
                fakeRequest
            )
        }
        print("Result from the backend: $result")
        return result.body<CombinationResponse>()
    }
}