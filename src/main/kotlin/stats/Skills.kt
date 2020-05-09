package stats

import kotlinx.serialization.Serializable
import stats.Skill.*

@Serializable
class Skills(
    val physical: Map<Skill, Int>,
    val social: Map<Skill, Int>,
    val mental: Map<Skill, Int>
) {
    fun all(): Map<Skill, Int> {
        var m = mutableMapOf<Skill, Int>()
        m.putAll(physical)
        m.putAll(social)
        m.putAll(mental)
        return m
    }
}

const val totalSkillCount = 27
val skillDistributionOptions = listOf("jackOfAllTrades", "balanced", "specialist")

enum class SkillType {
    PHYSICAL,
    SOCIAL,
    MENTAL
}

enum class Skill(val skillType: SkillType) {
    ATHLETICS(SkillType.PHYSICAL),
    BRAWL(SkillType.PHYSICAL),
    CRAFT(SkillType.PHYSICAL),
    DRIVE(SkillType.PHYSICAL),
    FIREARMS(SkillType.PHYSICAL),
    MELEE(SkillType.PHYSICAL),
    LARCENY(SkillType.PHYSICAL),
    STEALTH(SkillType.PHYSICAL),
    SURVIVAL(SkillType.PHYSICAL),


    ANIMAL_KEN(SkillType.SOCIAL),
    ETIQUETTE(SkillType.SOCIAL),
    INSIGHT(SkillType.SOCIAL),
    INTIMIDATION(SkillType.SOCIAL),
    LEADERSHIP(SkillType.SOCIAL),
    PERFORMANCE(SkillType.SOCIAL),
    PERSUASION(SkillType.SOCIAL),
    STREETWISE(SkillType.SOCIAL),
    SUBTERFUGE(SkillType.SOCIAL),


    ACADEMICS(SkillType.MENTAL),
    AWARENESS(SkillType.MENTAL),
    FINANCE(SkillType.MENTAL),
    INVESTIGATION(SkillType.MENTAL),
    MEDICINE(SkillType.MENTAL),
    OCCULT(SkillType.MENTAL),
    POLITICS(SkillType.MENTAL),
    SCIENCE(SkillType.MENTAL),
    TECHNOLOGY(SkillType.MENTAL)
}

val skillsByType = mapOf(
    SkillType.PHYSICAL to listOf(
        ATHLETICS,
        BRAWL,
        CRAFT,
        DRIVE,
        FIREARMS,
        MELEE,
        LARCENY,
        STEALTH,
        SURVIVAL
    ),

    SkillType.SOCIAL to listOf(
        ANIMAL_KEN,
        ETIQUETTE,
        INSIGHT,
        INTIMIDATION,
        LEADERSHIP,
        PERFORMANCE,
        PERSUASION,
        STREETWISE,
        SUBTERFUGE
    ),
    
    SkillType.MENTAL to listOf(
        ACADEMICS,
        AWARENESS,
        FINANCE,
        INVESTIGATION,
        MEDICINE,
        OCCULT,
        POLITICS,
        SCIENCE,
        TECHNOLOGY
    )
)

fun physicalSkillsFromList(points: List<Int>): Map<Skill, Int> = mapOf(
    ATHLETICS to points[0],
    BRAWL to points[1],
    CRAFT to points[2],
    DRIVE to points[3],
    FIREARMS to points[4],
    MELEE to points[5],
    LARCENY to points[6],
    STEALTH to points[7],
    SURVIVAL to points[8]
)


fun socialSkillsFromList(points: List<Int>): Map<Skill, Int> = mapOf(
    ANIMAL_KEN to points[0],
    ETIQUETTE to points[1],
    INSIGHT to points[2],
    INTIMIDATION to points[3],
    LEADERSHIP to points[4],
    PERFORMANCE to points[5],
    PERSUASION to points[6],
    STREETWISE to points[7],
    SUBTERFUGE to points[8]
)

fun mentalSkillsFromList(points: List<Int>): Map<Skill, Int> = mapOf(
    ACADEMICS to points[0],
    AWARENESS to points[1],
    FINANCE to points[2],
    INVESTIGATION to points[3],
    MEDICINE to points[4],
    OCCULT to points[5],
    POLITICS to points[6],
    SCIENCE to points[7],
    TECHNOLOGY to points[8]
)
