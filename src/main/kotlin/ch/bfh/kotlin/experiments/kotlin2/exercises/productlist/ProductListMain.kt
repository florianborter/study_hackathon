package ch.bfh.kotlin.experiments.kotlin2.exercises.productlist

import java.io.*

fun main() {
    val productList = arrayListOf(
        Product(name = "Shoe", price = 89.9, quantity = 1),
        Product(name = "Shirt", price = 19.0, quantity = 3),
        Product(name = "Jacket", price = 99.9, quantity = 1)
    )

    val path = "src\\main\\resources\\products.data"

    ObjectOutputStream(FileOutputStream(path)).use { it.writeObject(productList) }

    try {
        ObjectInputStream(FileInputStream(path)).use {
            val c = it.readObject() as ArrayList<*>
            c.map { it as Product }
            c.forEach { println(it) }
        }
    } catch (e: ClassCastException) {
        System.err.println("unexpected object type")
    } catch (e: InvalidClassException) {
        System.err.println("class definition has changed")
    }
}