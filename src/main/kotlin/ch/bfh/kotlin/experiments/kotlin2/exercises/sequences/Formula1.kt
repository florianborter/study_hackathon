/** Programming 2 with Kotlin - FS 20/21,
 *  Computer Science, Bern University of Applied Sciences */

package ch.bfh.kotlin.experiments.kotlin2.exercises.sequences

import kotlin.random.Random

class F1Car {
    val team = teams[Random.nextInt(teams.size)]
    val maxSpeed = 250 + Random.nextInt(50)
    val driver = F1Driver()

    companion object {
        val teams =
            arrayOf("Mercedes", "Ferrari", "Williams", "RedBull", "Renault", "Sauber")
    }

    override fun toString(): String {
        return "ch.bfh.kotlin.experiments.kotlin2.exercises.sequences.F1Car(team='$team', maxSpeed=$maxSpeed, driver=$driver)"
    }
}

class F1Driver {
    val lastName = names[Random.nextInt(names.size)]
    val firstName = ('A' + Random.nextInt(26)) + "."
    val age = Random.nextInt(20) + 20
    val racesWon = Random.nextInt(10)

    companion object {
        var names =
            arrayOf("Hamilton", "Rosberg", "Vettel", "Raikkonen", "Bottas", "Massa")
    }

    override fun toString(): String {
        return "ch.bfh.kotlin.experiments.kotlin2.exercises.sequences.F1Driver(lastName='$lastName', firstName='$firstName', age=$age, racesWon=$racesWon)"
    }
}

fun main() {
    //Print the three teams with the fastest cars.
    generateSequence { F1Car() }.take(3).sortedByDescending { it.maxSpeed }.map { it.team }.forEach { println(it) }

    val numberOfCarsToGenerate = 10

    //Print the average speed of the cars with a driver who has won at least 8 races.
    val a = generateSequence { F1Car() }.filter { it.driver.racesWon > 8 }.take(numberOfCarsToGenerate).map { it.maxSpeed }.average()
    println(a)

    //Print the first and last names of the 3 drivers who have won the smallest amount of races.
    generateSequence { F1Car() }.take(numberOfCarsToGenerate)
        .sortedBy { it.driver.racesWon }
        .take(3)
        .map { "${it.driver.firstName} ${it.driver.lastName}" }
        .forEach { println(it) }
}
