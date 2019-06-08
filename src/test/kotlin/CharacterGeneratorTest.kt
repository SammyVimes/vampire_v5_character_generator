import configs.loadDefaultPointsToSpend
import org.junit.jupiter.api.Test

internal class CharacterGeneratorTest {

    @Test
    fun smokeTestCharacterGeneration() {
        val character = CharacterGenerator().generateCharacter()
    }

    @Test
    fun testGenerateSkills() {
        val characterGenerator = CharacterGenerator()
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel)

            assert(skills.physical.values.all { it in 0..5 })
            assert(skills.social.values.all { it in 0..5 })
            assert(skills.mental.values.all { it in 0..5 })

            val physicalSum = skills.physical.values.sum()
            val socialSum = skills.social.values.sum()
            val mentalSum = skills.mental.values.sum()
            assert(physicalSum + socialSum + mentalSum == availablePointsByLevel.map { it.key * it.value }.sum())
            // TODO: assert sanity for skill distribution
        }
    }

    // TODO: similar tests for attributes and disciplines
}