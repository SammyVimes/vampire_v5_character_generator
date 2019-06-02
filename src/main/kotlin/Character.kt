data class Character(
    val attributes: Attributes,

    var health: Int,
    var willpower: Int,
    var humanity: Int,
    var bloodPotency: Int,

    val levelsByDisciplines: Map<Discipline, Int>
) {

}