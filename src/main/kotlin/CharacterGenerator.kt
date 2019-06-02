import configs.*

class CharacterGenerator {
    private val defaultPointsToSpend = loadDefaultPointsToSpend()
    private val playerCharacterTypes = loadPlayerCharacterTypes()
    private val mortalCharacterTypes = loadMortalCharacterTypes()
    private val names = loadNames()

    fun generateMortal() {
        val (firstName, lastName) = generateName()

        val attributes = generateAttributes(defaultPointsToSpend.attributes)
        val skills = generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue("balanced"))
        val disciplines = generateDisciplinePowers(defaultPointsToSpend.disciplineLevels)
    }

    fun generateCharacter(): Character {
        val (firstName, lastName) = generateName()

        val attributes = generateAttributes(defaultPointsToSpend.attributes)
        val skills =
            generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue(skillDistributionOptions.random()))
        val disciplines = generateDisciplinePowers(defaultPointsToSpend.disciplineLevels)

        val health = 3 + attributes.physical.getValue(PhysicalAttribute.STAMINA) // + fortitude discipline bonus

        // TODO: fill with actual values
        return Character(attributes, skills, listOf(), health, -1, -1, -1, mapOf())
    }

    fun generateName(): Pair<String, String> {
        val gender = listOf("male", "female").random()
        val country = validNameCountries.random()

        val firstName = names.getValue(country).getByGender(gender).random()
        val lastName = names.getValue(country).surnames.random()

        return Pair(firstName, lastName)
    }

    fun generateAttributes(pointsByLevel: PointsByLevel): Attributes {
        val pointList = pointsByLevelToPointList(pointsByLevel).fillWith(0, totalAttributeCount).shuffled()

        val physical = physicalAttributesFromList(pointList.subList(0, 2))
        val social = socialAttributesFromList(pointList.subList(3, 5))
        val mental = mentalAttributesFromList(pointList.subList(6, 8))

        return Attributes(physical, social, mental)
    }

    fun generateSkills(pointsByLevel: PointsByLevel): Skills {
        val pointList = pointsByLevelToPointList(pointsByLevel).fillWith(0, totalSkillCount).shuffled()

        val physical = physicalSkillsFromList(pointList.subList(0, 7))
        val social = socialSkillsFromList(pointList.subList(8, 15))
        val mental = mentalSkillsFromList(pointList.subList(16, 23))

        return Skills(physical, social, mental)
    }

    fun generateDisciplinePowers(levels: List<Int>): List<DisciplinePower> {
        // TODO: generate actual discipline values
        return listOf()
    }

    fun pointsByLevelToPointList(pointsByLevel: PointsByLevel) =
        pointsByLevel.flatMap { (points, level) -> (0..level).map { points } }
}