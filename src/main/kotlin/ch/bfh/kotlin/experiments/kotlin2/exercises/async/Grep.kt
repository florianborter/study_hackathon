package ch.bfh.kotlin.experiments.kotlin2.exercises.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.io.path.Path

fun grep(grepString: String, pathString: String) {
    runBlocking {
        File(pathString).walkTopDown().forEach {
            launch(Dispatchers.Default) {
                println("started a coroutine")
                it.readLines().forEachIndexed { index, line ->
                    if (line.contains(grepString)) {
                        println("found '$grepString' in file '${it.name}' on line number $index")
                    }
                }
            }
        }
    }
}

fun main() {
    // Example file
    val dir = Path("src/main/kotlin/ch/bfh/kotlin/experiments/Playground.kt").toAbsolutePath()
    println(dir)
    grep("font", dir.toString())
}