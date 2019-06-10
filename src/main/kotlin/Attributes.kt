import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val physical: Map<PhysicalAttribute, Int>,
    val social: Map<SocialAttribute, Int>,
    val mental: Map<MentalAttribute, Int>
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

const val totalAttributeCount = 9

enum class PhysicalAttribute {
    STRENGTH,
    DEXTERITY,
    STAMINA
}

fun physicalAttributesFromList(points: List<Int>): Map<PhysicalAttribute, Int> = mapOf(
    PhysicalAttribute.STRENGTH to points[0],
    PhysicalAttribute.DEXTERITY to points[1],
    PhysicalAttribute.STAMINA to points[2]
)

enum class SocialAttribute {
    CHARISMA,
    MANIPULATION,
    COMPOSURE
}

fun socialAttributesFromList(points: List<Int>): Map<SocialAttribute, Int> = mapOf(
    SocialAttribute.CHARISMA to points[0],
    SocialAttribute.MANIPULATION to points[1],
    SocialAttribute.COMPOSURE to points[2]
)

enum class MentalAttribute {
    INTELLIGENCE,
    WITS,
    RESOLVE
}

fun mentalAttributesFromList(points: List<Int>): Map<MentalAttribute, Int> = mapOf(
    MentalAttribute.INTELLIGENCE to points[0],
    MentalAttribute.WITS to points[1],
    MentalAttribute.RESOLVE to points[2]
)