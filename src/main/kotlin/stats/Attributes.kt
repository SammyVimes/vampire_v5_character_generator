package stats

import kotlinx.serialization.Serializable
import stats.Attribute.*


@Serializable
data class Attributes(
    val physical: Map<Attribute, Int>,
    val social: Map<Attribute, Int>,
    val mental: Map<Attribute, Int>
) {
    fun getByType(type: String): Map<Any, Int> {
        when (type) {
            "physical" -> physical
            "social" -> social
            "mental" -> mental
        }

        throw RuntimeException("$type not valid!")
    }

    fun all(): Map<Attribute, Int> {
        var m = mutableMapOf<Attribute, Int>()
        m.putAll(physical)
        m.putAll(social)
        m.putAll(mental)
        return m
    }
}

const val totalAttributeCount = 9

enum class AttributeType {
    PHYSICAL,
    SOCIAL,
    MENTAL
}

enum class Attribute(val attributeType: AttributeType) {
    STRENGTH(AttributeType.PHYSICAL),
    DEXTERITY(AttributeType.PHYSICAL),
    STAMINA(AttributeType.PHYSICAL),

    CHARISMA(AttributeType.SOCIAL),
    MANIPULATION(AttributeType.SOCIAL),
    COMPOSURE(AttributeType.SOCIAL),

    INTELLIGENCE(AttributeType.MENTAL),
    WITS(AttributeType.MENTAL),
    RESOLVE(AttributeType.MENTAL)
}

val attributesByType = mapOf(
    AttributeType.PHYSICAL to listOf(STRENGTH, DEXTERITY, STAMINA),
    AttributeType.SOCIAL to listOf(CHARISMA, MANIPULATION, COMPOSURE),
    AttributeType.MENTAL to listOf(INTELLIGENCE, WITS, RESOLVE)
)

fun physicalAttributesFromList(points: List<Int>): Map<Attribute, Int> = mapOf(
    STRENGTH to points[0],
    DEXTERITY to points[1],
    STAMINA to points[2]
)

fun socialAttributesFromList(points: List<Int>): Map<Attribute, Int> = mapOf(
    CHARISMA to points[0],
    MANIPULATION to points[1],
    COMPOSURE to points[2]
)

fun mentalAttributesFromList(points: List<Int>): Map<Attribute, Int> = mapOf(
    INTELLIGENCE to points[0],
    WITS to points[1],
    RESOLVE to points[2]
)
