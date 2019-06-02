data class Character(
    val attributes: Attributes,

    var health: Int,
    var willpower: Int,

    val levelsByDisciplines: Map<Discipline, Int>
) {

}