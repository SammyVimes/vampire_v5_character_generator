package configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

typealias PointsByLevel = Map<Int, Int>
typealias SkillsByLevelOptions = Map<String, PointsByLevel>
typealias Country = String

val configMapper: ObjectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())


