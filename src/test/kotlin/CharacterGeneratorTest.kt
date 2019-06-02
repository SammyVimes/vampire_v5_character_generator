import configs.loadDefaultPointsToSpend
import org.junit.jupiter.api.Test

internal class CharacterGeneratorTest {

    @Test
    fun testGenerateSkills() {
        val characterGenerator = CharacterGenerator()
        val defaultPointsToSpend = loadDefaultPointsToSpend()

        val skills = characterGenerator.generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue("balanced"))

        // TODO: assert sanity for skill distribution
    }

    // TODO: similar tests for attributes and disciplines
}