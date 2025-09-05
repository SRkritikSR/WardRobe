import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class  CombinationResponse(
    val data: List<CombinationResult>,
    val meta: Meta
)

@Serializable
data class CombinationResult(
    val indexes: Indexes,
    val scores: Score
)

@Serializable
data class Indexes(
    val topIndex: Int,
    val bottomIndex: Int,
    val footwearIndex: Int
)

@Serializable
data class Score(
    val avgEmbedding: Double
)

@Serializable
data class Meta(
    val totalCombination: Int,
    val promptUsed: String
)