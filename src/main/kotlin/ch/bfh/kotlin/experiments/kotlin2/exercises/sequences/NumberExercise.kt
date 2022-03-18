package ch.bfh.kotlin.experiments.kotlin2.exercises.sequences

import kotlin.random.Random

fun main() {
    println("average: ${generateSequence { Random.nextInt(10) }.take(100).filter { it != 0 }.average()}")
    println("count: ${generateSequence { Random.nextInt(10) }.take(100).count { it == 7 }}")
    println("contains only 0-9: ${generateSequence { Random.nextInt(10) }.take(100).all { it in 0..9 }}")

    println("count per number: ${generateSequence { Random.nextInt(10) }.take(100).groupingBy { it }.eachCount()}")
    println(generateSequence { Random.nextInt(10) }.take(100).distinct().map { it * it }.joinToString(",", "Result: "))
    val res = generateSequence { Random.nextInt(10) }.take(100).distinct().filter { it != 0 }.reduce { first, second -> first * second }
    println("values multiplied: $res")
}