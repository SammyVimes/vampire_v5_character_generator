data class Character(
    val attributes: Attributes,
    val skills: Skills,
    val disciplines: List<DisciplinePower>,

    var maxHealth: Int,
    var willpower: Int,
    var humanity: Int,
    var bloodPotency: Int,

    val levelsByDisciplines: Map<Discipline, Int>
) {

}