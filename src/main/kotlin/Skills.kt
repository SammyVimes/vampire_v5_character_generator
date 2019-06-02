data class Skills(
    val physical: Map<PhysicalSkill, Int>,
    val social: Map<SocialSkill, Int>,
    val mental: Map<MentalSkill, Int>
)

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