package configs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
            assertTrue(attributes.all { it.key in 1..5 })
            assertEquals(9, attributes.map { it.value }.sum())
        }
    }

    @Test
    fun defaultSkillPointsAreSane() {
        with(defaultPointsToSpend) {
            assertTrue(skillsByLevelOptions.all { it.value.all { skillsByLevel -> skillsByLevel.key in 1..5 } })
            assertTrue(skillsByLevelOptions.all { it.value.map { skillsByLevel -> skillsByLevel.value }.sum() <= 24 })
        }
    }

    @Test
    fun defaultDisciplinePointsAreSane() {
        with(defaultPointsToSpend) {
            assertTrue(disciplineLevels.all { it in 1..5 })
        }
    }

    @Test
    fun defaultAdvantagePointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assertTrue(advantages >= 0)
            assertTrue(flaws >= 0)
        }
    }

}