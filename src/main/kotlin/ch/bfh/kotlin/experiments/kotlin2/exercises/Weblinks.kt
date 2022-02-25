package ch.bfh.kotlin.experiments.kotlin2.exercises

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.FileInputStream
import java.net.URL

fun main(args: Array<String>) {
    val input = URL("https://www.uni-muenster.de/Sprachwissenschaft/en/forschung/linksammlung/index.html").openStream().reader()
    val reader = BufferedReader(input)
    val output = mutableListOf<String>()
    try {
        var line = reader.readLine()
        while (line != null) {
            val match = Regex("href=\"(.*?)\"").find(line)
            if(match != null) {
                output.add(match.value)
                println(match.value)
            }
            line = reader.readLine()
        }
    } finally {
        reader.close()
    }
}