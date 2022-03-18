package ch.bfh.kotlin.experiments.kotlin2.exercises.serialization.jsontoxml

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.FileWriter
import java.nio.file.Paths
import java.time.LocalDate

fun main() {
    val filepath = "src\\main\\resources\\people.json"
    val mapper = jacksonObjectMapper()
    mapper.registerModule(JavaTimeModule())
    val personList = mapper.readValue(Paths.get(filepath).toFile(), People::class.java)
    println(personList)

    val xmlFilepath = "src\\main\\resources\\people.xml"
    val xmlMapper = XmlMapper()
    xmlMapper.registerModule(JavaTimeModule())
    xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    xmlMapper.writeValue(FileWriter(xmlFilepath), personList)
}

@JsonPropertyOrder("id", "firstName", "lastName", "professions", "birthData")
class Person(
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    val id: Long,
    val firstName: String,
    val lastName: String,
    @JacksonXmlProperty(localName = "born")
    val birthData: BirthData = BirthData(LocalDate.MIN, "", "CH")
) {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "profession")
    val professions = ArrayList<String>()
}

@JacksonXmlRootElement(localName = "people")
class People(
    @JsonIgnore
    val name: String = ""
) {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "person")
    val persons = ArrayList<Person>()
}

data class BirthData(val date: LocalDate, val city: String, val country: String)
