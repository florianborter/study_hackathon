package ch.bfh.kotlin.experiments.kotlin2.exercises.serialization.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.FileReader
import java.nio.file.Paths


@ExperimentalSerializationApi
fun main() {
    val filepath = "src\\main\\resources\\geography.json"
    val mapper = jacksonObjectMapper()
    val geography = mapper.readValue(Paths.get(filepath).toFile(), Geography::class.java)
    println(geography)

    val s = FileReader(filepath).readText()
    val geography1 = Json.decodeFromString<Geography>(s)
    println(geography1)
}

@Serializable
data class Geography(val name: String, val capital: Capital, val languages: ArrayList<String>, val area: Int, val cantons: ArrayList<Canton>)

@Serializable
data class Capital(val name: String)

@Serializable
data class Canton(val name: String, val abbreviation: String)