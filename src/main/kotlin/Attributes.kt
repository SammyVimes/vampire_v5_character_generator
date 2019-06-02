data class Attributes(
    val physical: Map<PhysicalAttribute, Int>,
    val social: Map<SocialAttribute, Int>,
    val mental: Map<MentalAttribute, Int>,

    var health: Int,
    var willpower: Int,
    val levelsByDisciplines: Map<Discipline, Int>
) {
    fun getByType(type: String): Map<Any, Int> {
        when (type) {
            "physical" -> physical
            "social" -> social
            "mental" -> mental
        }

        throw RuntimeException("$type not valid!")
    }
}

enum class PhysicalAttribute {
    STRENGTH,
    DEXTERITY,
    STAMINA
}

enum class SocialAttribute {
    CHARISMA,
    MANIPULATION,
    COMPOSURE
}

enum class MentalAttribute {
    INTELLIGENCE,
    WITS,
    RESOLVE
}