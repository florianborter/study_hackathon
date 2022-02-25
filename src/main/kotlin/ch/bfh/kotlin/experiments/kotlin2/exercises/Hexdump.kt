package ch.bfh.kotlin.experiments.kotlin2.exercises

import java.io.FileInputStream

fun main(args: Array<String>) {
    val inFile = FileInputStream("src\\main\\kotlin\\ch\\bfh\\kotlin\\experiments\\kotlin2\\exercises\\Hexdump.kt")
    var byte = inFile.read()
    while (byte != -1) {
        print(String.format("%02X ", byte))
        byte = inFile.read()
    }
    inFile.close()
}