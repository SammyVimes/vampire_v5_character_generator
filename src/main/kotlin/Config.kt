import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

typealias PointsByLevel = Map<Int, Int>
typealias SkillsByLevelOptions = Map<String, PointsByLevel>

private val configMapper: ObjectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())

data class DefaultPointsToSpend(
    val attributes: PointsByLevel,
    val skillsByLevelOptions: SkillsByLevelOptions,
    val disciplineLevels: List<Int>,
    val advantages: Int,
    val flaws: Int
)

fun loadDefaultPointsToSpend(): DefaultPointsToSpend {
    val defaultPointsText = PlayerCharacterType::class.java.getResource("default_points_to_spend.yaml").readText()
    return configMapper.readValue(defaultPointsText)
}

data class PlayerCharacterType(
    val bloodPotency: Int,
    val humanity: Int,
    val experience: Int,
    val generations: List<Int>
)

fun loadPlayerCharacterTypes(): Map<String, PlayerCharacterType> {
    val characterTypeText = PlayerCharacterType::class.java.getResource("player_character_types.yaml").readText()
    return configMapper.readValue(characterTypeText)
}