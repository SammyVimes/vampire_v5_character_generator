package configs

import com.fasterxml.jackson.module.kotlin.readValue

data class Names(
    val male: List<String>,
    val female: List<String>,
    val surnames: List<String>
) {
    fun getByGender(gender: String): List<String> {
        return when (gender) {
            "male" -> male
            "female" -> female
            else -> listOf("")
        }
    }
}


fun loadNames(): Map<Country, Names> {
    val namesText = Names::class.java.getResource("../names.yaml").readText()
    return configMapper.readValue(namesText)
}