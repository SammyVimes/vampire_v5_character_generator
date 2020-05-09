package fillable

import VTMCharacter
import com.lowagie.text.pdf.AcroFields
import stats.*

val attrs: Array<String> = arrayOf(
    "Strength", "Dexterity", "Stamina",
    "Charisma", "Manipulation", "Composure",
    "Intelligence", "Wits", "Resolve"
)

// first is the column, second is row
val v: Map<String, Pair<Int, Int>> = mapOf(
    "Strength" to Pair(0, 0),  "Dexterity" to Pair(0, 1), "Stamina" to Pair(0, 2),
    "Charisma" to Pair(1, 0), "Manipulation" to Pair(1, 1), "Composure" to Pair(1, 2),
    "Intelligence" to Pair(2, 0), "Wits" to Pair(2, 1), "Resolve" to Pair(2, 2)
)

fun getAttrPoint(attr: Attribute, point: Int): String {
    val row = attributesByType[attr.attributeType]!!.indexOf(attr)
    val column = when (attr.attributeType) {
        AttributeType.PHYSICAL -> 0
        AttributeType.SOCIAL -> 1
        AttributeType.MENTAL -> 2
    }
    return "Attributes.$point.$row.$column"
}

fun getSkillPoint(skill: Skill, point: Int): String {
    val row = skillsByType[skill.skillType]!!.indexOf(skill)
    val column = when (skill.skillType) {
        SkillType.PHYSICAL -> 0
        SkillType.SOCIAL -> 1
        SkillType.MENTAL -> 2
    }
    return "Check Box23.$point.$row.$column"
}

val nameForm = "Name.0.0"
val clan = "Clan"
val generation = "Generation"

fun fill(form: AcroFields, character: VTMCharacter) {
    character.attributes.all().forEach{t, u ->
        val attrLocation = getAttrPoint(t, u - 1)
        form.setField(attrLocation, "Yes");
    }

    character.skills.all().forEach {t, u ->
        if (u == 0) {
            return@forEach
        }
        val skillLocation = getSkillPoint(t, u - 1)
        form.setField(skillLocation, "Yes");
    }


    form.setField(nameForm, "${character.firstName} ${character.lastName}")
    form.setField(generation, character.generation.toString())
    form.setField(clan, "Ventrue")
}
