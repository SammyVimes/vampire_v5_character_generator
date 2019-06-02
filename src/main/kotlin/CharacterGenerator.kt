import configs.*

class CharacterGenerator {
    private val defaultPointsToSpend = loadDefaultPointsToSpend()
    private val playerCharacterTypes = loadPlayerCharacterTypes()
    private val mortalCharacterTypes = loadMortalCharacterTypes()
    private val names = loadNames()

    fun generateMortal() {
        val gender = listOf("male", "female").random()
        val country = "Germany"

        val firstName = names.getValue(country).getByGender(gender).random()
        val lastName = names.getValue(country).surnames.random()

        val attributes = generateAttributes(defaultPointsToSpend.attributes)
        val skills = generateSkills(defaultPointsToSpend.skillsByLevelOptions.getValue("balanced"))
        val disciplines = generateDisciplinePowers(defaultPointsToSpend.disciplineLevels)
    }

    fun generateAttributes(pointsByLevel: PointsByLevel): Attributes {
        val pointList = pointsByLevelToPointList(pointsByLevel).fillWith(0, totalAttributeCount).shuffled()

        val physical = physicalAttributesFromList(pointList.subList(0, 2))
        val social = socialAttributesFromList(pointList.subList(3, 5))
        val mental = mentalAttributesFromList(pointList.subList(6, 8))

        return Attributes(physical, social, mental)
    }

    fun generateSkills(pointsByLevel: PointsByLevel): Skills {
        // TODO: generate actual skill values
        return Skills(mapOf(), mapOf(), mapOf())
    }

    fun generateDisciplinePowers(levels: List<Int>): List<DisciplinePower> {
        // TODO: generate actual discipline values
        return listOf()
    }

    fun pointsByLevelToPointList(pointsByLevel: PointsByLevel) =
        pointsByLevel.flatMap { (points, level) -> (0..level).map { points } }
}