
import configs.Discipline
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Character(
    val attributes: Attributes,
    val skills: Skills,
    val disciplines: List<Discipline>,

    var maxHealth: Int,
    var willpower: Int,
    var bloodPotency: Int,
    var humanity: Int,
    var experience: Int,
    var generation: Int
) {
    fun toJson() = Json.stringify(Character.serializer(), this)
}