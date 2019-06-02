import configs.loadDefaultPointsToSpend
import org.junit.jupiter.api.Test

internal class CharacterGeneratorTest {

    @Test
    fun testGenerateSkills() {
        val characterGenerator = CharacterGenerator()
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        for (skillDistribution in skillDistributionOptions) {
            val skills =
                characterGenerator.generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution))

            // TODO: assert sanity for skill distribution
        }
    }

    // TODO: similar tests for attributes and disciplines
}