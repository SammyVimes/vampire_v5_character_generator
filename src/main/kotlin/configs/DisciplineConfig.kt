package configs

import com.fasterxml.jackson.module.kotlin.readValue

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

data class DisciplinePower(
    val name: String,
    val dicePool: String,
    val cost: String,
    val system: String,
    val duration: String
)

fun loadFullDisciplines(): List<Discipline> {
    val disciplinesText = Discipline::class.java.getResource("../disciplines.yaml").readText()
    return configMapper.readValue(disciplinesText)
}