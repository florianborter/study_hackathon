package ch.bfh.kotlin.experiments.kotlin2.pretest

fun <T> merge(array1: Array<T>, array2: Array<T>): Array<T> {
    return array1 + array2
}

operator fun String.times(factor: Int) = this.repeat(factor)

open class Animal(val name: String = "") {
    open val sound = "???"
}

class Cat(name: String) : Animal(name) {
    override val sound = "Miau"
}

class Dog(name: String) : Animal(name) {
    override val sound = "Wuff"
}

fun Animal.feed(nbm: Int) {
    println(sound)
}

fun Cat.feed(nbm: Int) {
    repeat(nbm) {
        print(sound)
    }
    println()
}

fun main() {
    val myAnimals = listOf(Cat("Minka"), Cat("Fiffy"))
    for ((idx, a) in myAnimals.withIndex()) a.feed((idx+1)*2)
}