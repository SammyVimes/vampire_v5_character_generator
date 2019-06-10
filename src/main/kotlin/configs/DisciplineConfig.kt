package configs

import com.fasterxml.jackson.module.kotlin.readValue
import stats.Discipline

fun loadFullDisciplines(): List<Discipline> {
    val disciplinesText = Discipline::class.java.getResource("$configResourcePath/disciplines.yaml").readText()
    return configMapper.readValue(disciplinesText)
}