package configs

import com.fasterxml.jackson.module.kotlin.readValue


data class PlayerCharacterType(
    val bloodPotency: Int,
    val humanity: Int,
    val experience: Int,
    val generations: List<Int>
)

val validPlayerCharacterTypes = listOf("Childer", "Neonate", "Ancilla")

fun loadPlayerCharacterTypes(): Map<String, PlayerCharacterType> {
    val characterTypeText = PlayerCharacterType::class.java.getResource("player_character_types.yaml").readText()
    return configMapper.readValue(characterTypeText)
}

data class MortalCharacterType(
    val attributes: PointsByLevel,
    val skills: PointsByLevel,
    val advantages: Int,
    val flaws: Int
)

fun loadMortalCharacterTypes(): Map<String, MortalCharacterType> {
    val characterTypeText = MortalCharacterType::class.java.getResource("$configResourcePath/mortal_character_types.yaml").readText()
    return configMapper.readValue(characterTypeText)
}