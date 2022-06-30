package ch.bfh.kotlin.experiments.kotlin2.exercises.operatoroverloading

import kotlin.math.sqrt

data class Vector2d(val x: Double, val y: Double)

operator fun Vector2d.plus(other: Vector2d) = Vector2d(this.x + other.x, this.y + other.y)

operator fun Vector2d.minus(other: Vector2d) = Vector2d(this.x - other.x, this.y - other.y)

operator fun Vector2d.unaryMinus() = Vector2d(-this.x, -this.y)

operator fun Vector2d.times(factor: Double) = Vector2d(this.x * factor, this.y * factor)

operator fun Double.times(vector2d: Vector2d) = Vector2d(this * vector2d.x, this * vector2d.y)

operator fun Vector2d.times(vector2d: Vector2d) = this.x * vector2d.x + this.y * vector2d.y

fun mag(vector2d: Vector2d): Double = sqrt( vector2d.x * vector2d.x + vector2d.y * vector2d.y)

fun Vector2d.cross(vector2d: Vector2d): Double = this.x * vector2d.y - vector2d.x * this.y