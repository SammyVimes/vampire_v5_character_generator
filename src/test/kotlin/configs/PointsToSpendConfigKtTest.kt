package configs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class PointsToSpendConfigKtTest {
    private lateinit var defaultPointsToSpend: DefaultPointsToSpend

    @BeforeEach
    fun loadPoints() {
        defaultPointsToSpend = loadDefaultPointsToSpend()
    }

    @Test
    fun defaultAttributePointsAreSane() {
        with(defaultPointsToSpend) {
            assert(attributes.all { it.key in 1..5 })
            Assertions.assertEquals(9, attributes.map { it.value }.sum())
        }
    }

    @Test
    fun defaultSkillPointsAreSane() {
        with(defaultPointsToSpend) {
            assert(skillsByLevelOptions.all { it.value.all { skillsByLevel -> skillsByLevel.key in 1..5 } })
            assert(skillsByLevelOptions.all { it.value.map { skillsByLevel -> skillsByLevel.value }.sum() <= 24 })
        }
    }

    @Test
    fun defaultDisciplinePointsAreSane() {
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