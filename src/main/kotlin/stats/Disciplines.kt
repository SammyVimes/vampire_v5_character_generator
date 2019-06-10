package stats

import kotlinx.serialization.Serializable

@Serializable
data class Discipline(
    val name: String,
    var level: Int,
    val powersByLevel: Map<Int, List<DisciplinePower>>
) {
    fun getPowersUntilLevel(level: Int) = powersByLevel.filterKeys { it <= level }
}

enum class DisciplineName {
    ANIMALISM,
    AUSPEX,
    CELERITY,
    DOMINATE,
    FORTITUDE,
    OBFUSCATE,
    POTENCE,
    PRESENCE,
    PROTEAN,
    BLOOD_SORCERY,
    RITUALS,
    THIN_BLOOD_ALCHEMY
}

@Serializable
data class DisciplinePower(
    val name: String,
    val dicePool: String,
    val cost: String,
    val system: String,
    val duration: String
)