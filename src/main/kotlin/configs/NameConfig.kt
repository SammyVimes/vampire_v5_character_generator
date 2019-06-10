package configs

import com.fasterxml.jackson.module.kotlin.readValue

data class Names(
    val maleFirstNames: List<String>,
    val femaleFirstNames: List<String>,
    val maleSurnames: List<String>,
    val femaleSurnames: List<String>
) {
    fun getFirstNameByGender(gender: String): List<String> {
        return when (gender) {
            "maleFirstNames" -> maleFirstNames
            "femaleFirstNames" -> femaleFirstNames
            else -> listOf("")
        }
    }

    fun getSurnameByGender(gender: String): List<String> {
        return when (gender) {
            "maleFirstNames" -> maleSurnames
            "femaleFirstNames" -> femaleSurnames
            else -> listOf("")
        }
    }
}

val validNameCountries = listOf("Germany")

fun loadNames(): Map<Country, Names> {
    val namesText = Names::class.java.getResource("$configResourcePath/names.yaml").readText()
    return configMapper.readValue(namesText)
}