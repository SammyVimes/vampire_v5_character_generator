package configs

import org.junit.jupiter.api.Test

internal class CharacterTypeConfigKtTest {
    @Test
    fun playerCharacterTypesAreSane() {
        fun checkSanity(playerCharacterType: PlayerCharacterType) {
            with(playerCharacterType) {
                assert(bloodPotency in 0..10)
                assert(humanity in 1..10)
                assert(experience >= 0)
                assert(generations.all { it >= 0 })
            }
        }

        val playerCharacterTypes = loadPlayerCharacterTypes()
        playerCharacterTypes.forEach { checkSanity(it.value) }
    }

    @Test
    fun mortalCharacterTypesAreSane() {
        fun checkSanity(mortalCharacterType: MortalCharacterType) {
            with(mortalCharacterType) {
                assert(attributes.all { it.key in 1..5 })
                assert(attributes.map { it.value }.sum() == 9)

                assert(skills.all { it.key in 1..5 })
                assert(skills.map { it.value }.sum() <= 24)

                assert(advantages >= 0)
                assert(flaws >= 0)
            }
        }

        val mortalCharacterTypes = loadMortalCharacterTypes()
        mortalCharacterTypes.forEach { checkSanity(it.value) }
    }
}