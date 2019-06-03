import configs.*

class CharacterGenerator {
    private val defaultPointsToSpend = loadDefaultPointsToSpend()
    private val disciplines = loadDisciplines()
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

        val physical = physicalSkillsFromList(pointList.subList(0, 9))
        val social = socialSkillsFromList(pointList.subList(9, 18))
        val mental = mentalSkillsFromList(pointList.subList(18, 27))

        return Skills(physical, social, mental)
    }

    fun generateDisciplines(levels: List<Int>): List<Discipline> {
        val pickedDisciplines = mutableListOf<Discipline>()
        for (level in 1..levels.size) {
            pickedDisciplines.add((disciplines - pickedDisciplines).random())
        }
        pickedDisciplines.shuffle()

        for (level in levels) {

        }


        return listOf()
    }

    fun pointsByLevelToPointList(pointsByLevel: PointsByLevel) =
        pointsByLevel.flatMap { (points, level) -> (0..level).map { points } }
}