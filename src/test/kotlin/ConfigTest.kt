import org.junit.jupiter.api.Test

internal class ConfigTest {
    @Test
    fun defaultPointsAreSane() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        with(defaultPointsToSpend) {
            assert(attributes.all { it.key in 1..5 })
            assert(attributes.map { it.value }.sum() == 9)

            assert(skillsByLevelOptions.all { it.value.all { skillsByLevel -> skillsByLevel.key in 1..5 } })
            assert(skillsByLevelOptions.all { it.value.map { skillsByLevel -> skillsByLevel.value }.sum() <= 24 })

            assert(disciplineLevels.all { it > 0 })
            assert(advantages >= 0)
            assert(flaws >= 0)
        }
    }

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
            }
        }

        val mortalCharacterTypes = loadMortalCharacterTypes()
        mortalCharacterTypes.forEach { checkSanity(it.value) }
    }
}