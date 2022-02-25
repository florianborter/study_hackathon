package ch.bfh.kotlin.experiments.kotlin2.exercises

import java.io.FileReader

fun main(args: Array<String>) {
    val inFile = FileReader("src\\main\\kotlin\\ch\\bfh\\kotlin\\experiments\\kotlin2\\exercises\\testfile.txt")
    val lines = inFile.readLines()
    val chars = lines.joinToString().length
    val words = lines.joinToString().split(Regex("\\W+")).size

    FileProperties(chars = chars, words = words, lines = lines.size)

    println("chars: $chars words: $words lines: ${lines.size}")

    inFile.close()
}

data class FileProperties(
    val chars: Int,
    val words: Int,
    val lines: Int
)