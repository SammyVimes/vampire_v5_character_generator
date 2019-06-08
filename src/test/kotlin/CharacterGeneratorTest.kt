import configs.DefaultPointsToSpend
import configs.loadDefaultPointsToSpend
import configs.loadFullDisciplines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharacterGeneratorTest {
    private lateinit var characterGenerator: CharacterGenerator
    private lateinit var defaultPointsToSpend: DefaultPointsToSpend

    @BeforeEach
    fun setupGeneratorAndPoints() {
        characterGenerator = CharacterGenerator()
        defaultPointsToSpend = loadDefaultPointsToSpend()
    }

    @Test
    fun smokeTestCharacterGeneration() {
        characterGenerator.generateCharacter()
        characterGenerator.generateMortal()
    }

    @Test
    fun testGenerateAttributes() {
        val attributes = characterGenerator.generateAttributes(defaultPointsToSpend.attributes)

        assert(attributes.physical.values.all { it in 1..5 })
        assert(attributes.social.values.all { it in 1..5 })
        assert(attributes.mental.values.all { it in 1..5 })

        val physicalSum = attributes.physical.values.sum()
        val socialSum = attributes.social.values.sum()
        val mentalSum = attributes.mental.values.sum()

        val attributeSum = physicalSum + socialSum + mentalSum
        val availableAtrributePointSum = defaultPointsToSpend.attributes.map { it.key * it.value }.sum()
        assertEquals(availableAtrributePointSum, attributeSum)
    }

    @Test
    fun testGenerateSkills() {
        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel)

            assert(skills.physical.values.all { it in 0..5 })
            assert(skills.social.values.all { it in 0..5 })
            assert(skills.mental.values.all { it in 0..5 })

            val physicalSum = skills.physical.values.sum()
            val socialSum = skills.social.values.sum()
            val mentalSum = skills.mental.values.sum()

            val skillSum = physicalSum + socialSum + mentalSum
            val availableSkillPointSum = availablePointsByLevel.map { it.key * it.value }.sum()
            assertEquals(availableSkillPointSum, skillSum)
        }
    }

    @Test
    fun testGenerateDisciplines() {
        val fullDisciplines = loadFullDisciplines()
        val disciplines = characterGenerator.generateDisciplines(defaultPointsToSpend.disciplineLevels)

        assert(disciplines.all { it.name in fullDisciplines.map { fullDiscipline -> fullDiscipline.name } })
        assert(disciplines.all { it.level in 1..5 })
        disciplines.forEach {
            assertEquals(it.level, it.powersByLevel.values.flatten().size) }

        val validDisciplinePowerNames = fullDisciplines.flatMap {
            it.powersByLevel.values.flatten().map { power -> power.name }
        }
        assert(disciplines.all {
            it.powersByLevel.values.flatten().all { power -> power.name in validDisciplinePowerNames }
        })
    }
}