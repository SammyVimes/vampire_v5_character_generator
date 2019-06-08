package configs

import org.junit.jupiter.api.Test

internal class PointsToSpendConfigKtTest {
    @Test
    fun defaultAttributePointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assert(attributes.all { it.key in 1..5 })
            assert(attributes.map { it.value }.sum() == 9)
        }
    }

    @Test
    fun defaultSkillPointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assert(skillsByLevelOptions.all { it.value.all { skillsByLevel -> skillsByLevel.key in 1..5 } })
            assert(skillsByLevelOptions.all { it.value.map { skillsByLevel -> skillsByLevel.value }.sum() <= 24 })
        }
    }

    @Test
    fun defaultDisciplinePointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assert(disciplineLevels.all { it in 1..5 })
        }
    }

    @Test
    fun defaultAdvantagePointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assert(advantages >= 0)
            assert(flaws >= 0)
        }
    }

}