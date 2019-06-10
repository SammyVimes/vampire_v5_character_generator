import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import stats.Attributes
import stats.Discipline
import stats.Skills

@Serializable
data class Character(
    val firstName: String,
    val lastName: String,

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
    @UnstableDefault
    fun toJson() = Json(JsonConfiguration(prettyPrint = true)).stringify(serializer(), this)
}