package ch.bfh.kotlin.experiments.kotlin2.exercises.async

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

fun generateFile() {
    runBlocking {
        for (i in 0..9) {
            launch(Dispatchers.IO) {
                println("launched coroutine #$i")
                val fileName = "$i.txt"
                val file = File(fileName)
                println(file.path.toString())
                file.createNewFile()
                file.writeText("")
                for (j in 0..100) {
                    val random = Random(System.nanoTime()).nextInt(0..1000)
                    file.appendText("$random\n")
                }
            }
            println(i)
        }
    }
}

fun main() {
    generateFile()
}