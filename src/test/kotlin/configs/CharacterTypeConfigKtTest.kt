package configs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CharacterTypeConfigKtTest {
    @Test
    fun playerCharacterTypesAreSane() {
        fun checkSanity(playerCharacterType: PlayerCharacterType) {
            with(playerCharacterType) {
                assertTrue(bloodPotency in 0..10)
                assertTrue(humanity in 1..10)
                assertTrue(experience >= 0)
                assertTrue(generations.all { it >= 0 })
            }
        }

        val playerCharacterTypes = loadPlayerCharacterTypes()
        playerCharacterTypes.forEach { checkSanity(it.value) }
    }

    @Test
    fun mortalCharacterTypesAreSane() {
        fun checkSanity(mortalCharacterType: MortalCharacterType) {
            with(mortalCharacterType) {
                assertTrue(attributes.all { it.key in 1..5 })
                assertEquals(9, attributes.map { it.value }.sum())

                assertTrue(skills.all { it.key in 1..5 })
                assertTrue(skills.map { it.value }.sum() <= 24)

                assertTrue(advantages >= 0)
                assertTrue(flaws >= 0)
            }
        }

        val mortalCharacterTypes = loadMortalCharacterTypes()
        mortalCharacterTypes.forEach { checkSanity(it.value) }
    }
}