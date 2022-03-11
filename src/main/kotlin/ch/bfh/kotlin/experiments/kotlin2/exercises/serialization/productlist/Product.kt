package ch.bfh.kotlin.experiments.kotlin2.exercises.serialization.productlist

import java.io.Serializable

data class Product (val name: String, val price: Double = 0.0, val quantity: Int = 0): Serializable