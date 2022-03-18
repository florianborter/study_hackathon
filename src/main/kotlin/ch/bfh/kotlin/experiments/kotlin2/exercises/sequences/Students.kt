/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.sequences

import kotlin.random.Random

class Student {
    val firstName = firstNames[Random.nextInt(firstNames.count())]
    val lastName = lastNames[Random.nextInt(lastNames.count())]
    val semester = Random.nextInt(10) + 1
    val averageGrade = 5 * Random.nextDouble() + 1

    companion object {
        val firstNames = arrayOf("Tom", "Ben", "Joe", "Andy", "Pete", "Sam")
        val lastNames = arrayOf("Smith", "Miller", "Jordan", "Wright", "Bush")
    }

    override fun toString(): String {
        return "Student(firstName='$firstName', lastName='$lastName', semester=$semester, averageGrade=$averageGrade)"
    }
}

fun main() {
    val a = generateSequence { Student() }
        .filter { it.semester == 6 }
        .take(10)
        .sortedWith(compareBy({ it.firstName }, { it.lastName }))
        .map { "${it.firstName} ${it.lastName.uppercase()}" }
        .joinToString(", ")
    println(a)

    generateSequence { Student() }
        .take(10)
        .filter { it.averageGrade >= 4 }
        .groupingBy { it.semester }
        .eachCount()
        .forEach { println(it) }
}
