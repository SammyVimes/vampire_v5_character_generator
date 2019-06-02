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
        // TODO: generate actual attribute values
        return Attributes(mapOf(), mapOf(), mapOf(), 0, 0, mapOf())
    }

    fun generateSkills(pointsByLevel: PointsByLevel): Skills {
        // TODO: generate actual skill values
        return Skills(mapOf(), mapOf(), mapOf())
    }

    fun generateDisciplinePowers(levels: List<Int>): List<DisciplinePower> {
        // TODO: generate actual discipline values
        return listOf()
    }
}