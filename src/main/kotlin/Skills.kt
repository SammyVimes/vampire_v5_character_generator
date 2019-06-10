import kotlinx.serialization.Serializable

@Serializable
data class Skills(
    val physical: Map<PhysicalSkill, Int>,
    val social: Map<SocialSkill, Int>,
    val mental: Map<MentalSkill, Int>
)

const val totalSkillCount = 27
val skillDistributionOptions = listOf("jackOfAllTrades", "balanced", "specialist")

enum class PhysicalSkill {
    ATHLETICS,
    BRAWL,
    CRAFT,
    DRIVE,
    FIREARMS,
    MELEE,
    LARCENY,
    STEALTH,
    SURVIVAL
}

fun physicalSkillsFromList(points: List<Int>): Map<PhysicalSkill, Int> = mapOf(
    PhysicalSkill.ATHLETICS to points[0],
    PhysicalSkill.BRAWL to points[1],
    PhysicalSkill.CRAFT to points[2],
    PhysicalSkill.DRIVE to points[3],
    PhysicalSkill.FIREARMS to points[4],
    PhysicalSkill.MELEE to points[5],
    PhysicalSkill.LARCENY to points[6],
    PhysicalSkill.STEALTH to points[7],
    PhysicalSkill.SURVIVAL to points[8]
)

enum class SocialSkill {
    ANIMAL_KEN,
    ETIQUETTE,
    INSIGHT,
    INTIMIDATION,
    LEADERSHIP,
    PERFORMANCE,
    PERSUASION,
    STREETWISE,
    SUBTERFUGE
}

fun socialSkillsFromList(points: List<Int>): Map<SocialSkill, Int> = mapOf(
    SocialSkill.ANIMAL_KEN to points[0],
    SocialSkill.ETIQUETTE to points[1],
    SocialSkill.INSIGHT to points[2],
    SocialSkill.INTIMIDATION to points[3],
    SocialSkill.LEADERSHIP to points[4],
    SocialSkill.PERFORMANCE to points[5],
    SocialSkill.PERSUASION to points[6],
    SocialSkill.STREETWISE to points[7],
    SocialSkill.SUBTERFUGE to points[8]
)

enum class MentalSkill {
    ACADEMICS,
    AWARENESS,
    FINANCE,
    INVESTIGATION,
    MEDICINE,
    OCCULT,
    POLITICS,
    SCIENCE,
    TECHNOLOGY
}

fun mentalSkillsFromList(points: List<Int>): Map<MentalSkill, Int> = mapOf(
    MentalSkill.ACADEMICS to points[0],
    MentalSkill.AWARENESS to points[1],
    MentalSkill.FINANCE to points[2],
    MentalSkill.INVESTIGATION to points[3],
    MentalSkill.MEDICINE to points[4],
    MentalSkill.OCCULT to points[5],
    MentalSkill.POLITICS to points[6],
    MentalSkill.SCIENCE to points[7],
    MentalSkill.TECHNOLOGY to points[8]
)