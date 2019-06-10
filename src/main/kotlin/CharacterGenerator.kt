import configs.*
import stats.*

class CharacterGenerator {
    private val defaultPointsToSpend = loadDefaultPointsToSpend()
    private val fullDisciplines = loadFullDisciplines()
    private val playerCharacterTypes = loadPlayerCharacterTypes()
    private val mortalCharacterTypes = loadMortalCharacterTypes()
    private val names = loadNames()

    fun generateMortal() {
        val (firstName, lastName) = generateName()

        val attributes = generateAttributes(defaultPointsToSpend.attributes)
        val skills = generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue("balanced"))
        val disciplines = generateDisciplines(defaultPointsToSpend.disciplineLevels)
    }

    fun generateCharacter(): Character {
        val (firstName, lastName) = generateName()

        val attributes = generateAttributes(defaultPointsToSpend.attributes)
        val skills =
            generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistributionOptions.random()))
        val disciplines = generateDisciplines(defaultPointsToSpend.disciplineLevels)

        val health = 3 + attributes.physical.getValue(PhysicalAttribute.STAMINA) // + fortitude discipline bonus
        val characterType = playerCharacterTypes.values.random()
        val willpower =
            attributes.social.getValue(SocialAttribute.COMPOSURE) + attributes.mental.getValue(MentalAttribute.RESOLVE)

        return Character(
            firstName,
            lastName,
            attributes,
            skills,
            disciplines,
            health,
            willpower,
            characterType.bloodPotency,
            characterType.humanity,
            characterType.experience,
            characterType.generations.random()
        )
    }

    private fun generateName(
        gender: String = listOf("maleFirstNames", "femaleFirstNames").random(),
        country: String = validNameCountries.random()
    ): Pair<String, String> {
        val firstName = names.getValue(country).getFirstNameByGender(gender).random()
        val lastName = names.getValue(country).getSurnameByGender(gender).random()

        return Pair(firstName, lastName)
    }

    fun generateAttributes(pointsByLevel: PointsByLevel, priority: AttributeType? = null): Attributes {
        val pointList = pointsByLevelToPointList(pointsByLevel).fillWith(0, totalAttributeCount).shuffled()
        val sortedPointList = sortAttributesPointList(pointList, priority)

        val physical = physicalAttributesFromList(sortedPointList.subList(0, 3))
        val social = socialAttributesFromList(sortedPointList.subList(3, 6))
        val mental = mentalAttributesFromList(sortedPointList.subList(6, 9))

        return Attributes(physical, social, mental)
    }

    fun generateSkills(pointsByLevel: PointsByLevel, priority: SkillType? = null): Skills {
        val pointList = pointsByLevelToPointList(pointsByLevel).fillWith(0, totalSkillCount).shuffled()
        val sortedPointList = sortSkillsPointList(pointList, priority)

        val physical = physicalSkillsFromList(sortedPointList.subList(0, 9))
        val social = socialSkillsFromList(sortedPointList.subList(9, 18))
        val mental = mentalSkillsFromList(sortedPointList.subList(18, 27))

        return Skills(physical, social, mental)
    }

    fun generateDisciplines(levels: List<Int>): List<Discipline> {
        val pickedDisciplines = mutableListOf<Discipline>()
        for (level in levels) {
            val pickedFullDiscipline = availableDisciplines(fullDisciplines, pickedDisciplines).random()
            val pickedDiscipline = Discipline(
                pickedFullDiscipline.name,
                level,
                pickedFullDiscipline.getPowersUntilLevel(level).map { it.key to listOf(it.value.random()) }.toMap()
            )

            pickedDisciplines.add(pickedDiscipline)
        }

        return pickedDisciplines
    }

    private fun pointsByLevelToPointList(pointsByLevel: PointsByLevel) =
        pointsByLevel.flatMap { (points, level) -> (1..level).map { points } }

    private fun sortAttributesPointList(pointList: List<Int>, priority: AttributeType?): List<Int> {
        val sortedList = pointList.sortedDescending()
        val prioritized = sortedList.subList(0, 3)
        val others = sortedList.subList(3, 9).shuffled()

        return when (priority) {
            null -> pointList
            AttributeType.PHYSICAL -> prioritized + others
            AttributeType.SOCIAL -> others.subList(0, 3) + prioritized + others.subList(3, 6)
            AttributeType.MENTAL -> others + prioritized
        }
    }

    private fun sortSkillsPointList(pointList: List<Int>, priority: SkillType?): List<Int> {
        val sortedList = pointList.sortedDescending()
        val prioritized = sortedList.subList(0, 9)
        val others = sortedList.subList(9, 27).shuffled()

        return when (priority) {
            null -> pointList
            SkillType.PHYSICAL -> prioritized + others
            SkillType.SOCIAL -> others.subList(0, 9) + prioritized + others.subList(9, 18)
            SkillType.MENTAL -> others + prioritized
        }
    }

    private fun availableDisciplines(fullDisciplines: List<Discipline>, pickedDisciplines: List<Discipline>) =
        fullDisciplines.filter { it.name !in pickedDisciplines.map { disc -> disc.name } }
}