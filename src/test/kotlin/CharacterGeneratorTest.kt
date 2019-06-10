import configs.DefaultPointsToSpend
import configs.loadDefaultPointsToSpend
import configs.loadFullDisciplines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import stats.*

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

        attributes.physical.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }
        attributes.social.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }
        attributes.mental.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }

        assertAttributeSumEqualsAvailablePointsSum(attributes)
    }

    @Test
    fun testGeneratePhysicalPrioritizedAttributes() {
        val attributes = characterGenerator.generateAttributes(defaultPointsToSpend.attributes, AttributeType.PHYSICAL)

        attributes.physical.values.forEach {
            assertTrue(it in 0..5, lvlMsg(it, 0..5))
            assertTrue(attributes.mental.values.all { mental -> mental <= it }, attributes.toString())
            assertTrue(attributes.social.values.all { social -> social <= it }, attributes.toString())
        }

        assertAttributeSumEqualsAvailablePointsSum(attributes)
    }

    @Test
    fun testGenerateSocialPrioritizedAttributes() {
        val attributes = characterGenerator.generateAttributes(defaultPointsToSpend.attributes, AttributeType.SOCIAL)

        attributes.social.values.forEach {
            assertTrue(it in 0..5, lvlMsg(it, 0..5))
            assertTrue(attributes.physical.values.all { physical -> physical <= it }, attributes.toString())
            assertTrue(attributes.mental.values.all { mental -> mental <= it }, attributes.toString())
        }

        assertAttributeSumEqualsAvailablePointsSum(attributes)
    }

    @Test
    fun testGenerateMentalPrioritizedAttributes() {
        val attributes = characterGenerator.generateAttributes(defaultPointsToSpend.attributes, AttributeType.MENTAL)

        attributes.mental.values.forEach {
            assertTrue(it in 0..5, lvlMsg(it, 0..5))
            assertTrue(attributes.physical.values.all { mental -> mental <= it }, attributes.toString())
            assertTrue(attributes.social.values.all { social -> social <= it }, attributes.toString())
        }

        assertAttributeSumEqualsAvailablePointsSum(attributes)
    }

    @Test
    fun testGenerateSkills() {
        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel)

            skills.physical.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }
            skills.social.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }
            skills.mental.values.forEach { assertTrue(it in 0..5, lvlMsg(it, 0..5)) }

            val availableSkillPointSum = availablePointsByLevel.map { it.key * it.value }.sum()
            assertSkillSumEqualsAvailablePointsSum(skills, availableSkillPointSum)
        }
    }

    @Test
    fun testGeneratePhysicalPrioritizedSkills() {
        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel, SkillType.PHYSICAL)

            skills.physical.values.forEach {
                assertTrue(it in 0..5, lvlMsg(it, 0..5))
                assertTrue(skills.mental.values.all { mental -> mental <= it }, skills.toString())
                assertTrue(skills.social.values.all { social -> social <= it }, skills.toString())
            }

            val availableSkillPointSum = availablePointsByLevel.map { it.key * it.value }.sum()
            assertSkillSumEqualsAvailablePointsSum(skills, availableSkillPointSum)
        }
    }

    @Test
    fun testGenerateSocialPrioritizedSkills() {
        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel, SkillType.SOCIAL)

            skills.social.values.forEach {
                assertTrue(it in 0..5, lvlMsg(it, 0..5))
                assertTrue(skills.physical.values.all { physical -> physical <= it }, skills.toString())
                assertTrue(skills.mental.values.all { mental -> mental <= it }, skills.toString())
            }

            val availableSkillPointSum = availablePointsByLevel.map { it.key * it.value }.sum()
            assertSkillSumEqualsAvailablePointsSum(skills, availableSkillPointSum)
        }
    }

    @Test
    fun testGenerateMentalPrioritizedSkills() {
        for (skillDistribution in skillDistributionOptions) {
            val availablePointsByLevel = defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistribution)
            val skills = characterGenerator.generateSkills(availablePointsByLevel, SkillType.MENTAL)

            skills.mental.values.forEach {
                assertTrue(it in 0..5, lvlMsg(it, 0..5))
                assertTrue(skills.physical.values.all { mental -> mental <= it }, skills.toString())
                assertTrue(skills.social.values.all { social -> social <= it }, skills.toString())
            }

            val availableSkillPointSum = availablePointsByLevel.map { it.key * it.value }.sum()
            assertSkillSumEqualsAvailablePointsSum(skills, availableSkillPointSum)
        }
    }

    @Test
    fun testGenerateDisciplines() {
        val fullDisciplines = loadFullDisciplines()

        (1..20).forEach { _ ->
            val disciplines = characterGenerator.generateDisciplines(defaultPointsToSpend.disciplineLevels)

            assertEquals(disciplines, disciplines.distinctBy { it.name })

            disciplines.forEach {
                assertTrue(it.name in fullDisciplines.map { fullDiscipline -> fullDiscipline.name })

                assertTrue(it.level in 1..5, discLvlMsg(it, 1..5))

                assertEquals(
                    it.level, it.powersByLevel.values.flatten().size,
                    pwrAmtMsg(it)
                )
            }

            val validDisciplinePowerNames = fullDisciplines.flatMap {
                it.powersByLevel.values.flatten().map { power -> power.name }
            }
            disciplines.forEach {
                it.powersByLevel.values.flatten().forEach { power ->
                    assertTrue(
                        power.name in validDisciplinePowerNames,
                        discNamesMsg(power, validDisciplinePowerNames)
                    )
                }
            }
        }
    }

    private fun assertAttributeSumEqualsAvailablePointsSum(
        attributes: Attributes,
        availableAtrributePointSum: Int = defaultPointsToSpend.attributes.map { it.key * it.value }.sum()
    ) {
        val physicalSum = attributes.physical.values.sum()
        val socialSum = attributes.social.values.sum()
        val mentalSum = attributes.mental.values.sum()

        val attributeSum = physicalSum + socialSum + mentalSum
        assertEquals(availableAtrributePointSum, attributeSum)
    }

    private fun assertSkillSumEqualsAvailablePointsSum(
        skills: Skills,
        availableSkillPointSum: Int
    ) {
        val physicalSum = skills.physical.values.sum()
        val socialSum = skills.social.values.sum()
        val mentalSum = skills.mental.values.sum()

        val attributeSum = physicalSum + socialSum + mentalSum
        assertEquals(availableSkillPointSum, attributeSum)
    }

    private fun lvlMsg(level: Int, range: IntRange) = "Level is not within $range, but is $level"

    private fun discLvlMsg(discipline: Discipline, range: IntRange) =
        "${discipline.name} level is not within $range, but is ${discipline.level}"

    private fun discNamesMsg(power: DisciplinePower, validDisciplinePowerNames: List<String>) =
        "Power name ($power.name) is not in valid names ($validDisciplinePowerNames)"

    private fun pwrAmtMsg(discipline: Discipline) =
        "Amount of discipline powers (${discipline.powersByLevel.values.flatten().size}) is not equal to level (${discipline.level})"
}