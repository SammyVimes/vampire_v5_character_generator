package configs

import com.fasterxml.jackson.module.kotlin.readValue


data class DefaultPointsToSpend(
    val attributes: PointsByLevel,
    val skillsByLevelOptions: SkillsByLevelOptions,
    val disciplineLevels: List<Int>,
    val advantages: Int,
    val flaws: Int
)

fun loadDefaultPointsToSpend(): DefaultPointsToSpend {
    val defaultPointsText = DefaultPointsToSpend::class.java.getResource("../default_points_to_spend.yaml").readText()
    return configMapper.readValue(defaultPointsText)
}