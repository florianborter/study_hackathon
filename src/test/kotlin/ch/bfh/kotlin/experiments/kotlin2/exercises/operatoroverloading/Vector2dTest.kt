/** Programming 2 with Kotlin - FS 19/20,
 *  Computer Science, Bern University of Applied Sciences */
package ch.bfh.kotlin.experiments.kotlin2.exercises.operatoroverloading

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class Vector2dSpaceTest {

    /* Fixture */
    private val v1 = Vector2d(3.0, 4.0)
    private val v2 = Vector2d(2.0, 5.0)
    private val v3 = Vector2d(2.5, 5.5)
    private val v = Vector2d(2.0, 3.0)
    private val zero = Vector2d(0.0, 0.0)
    private val delta = 1.0E-5

    /* Checks properties of addition and assignment*/
    @Test
    internal fun plus() {
        assertEquals(Vector2d(5.0, 9.0), v1 + v2, "addition")
        assertEquals(v1, v1 + zero, "'+' neutral ")
        assertEquals(v1, zero + v1, "neutral '+'")
        assertEquals(v1 + v2, v2 + v1, "addition: commutativity")
        assertEquals((v1 + v2) + v3, v1 + (v2 + v3), "addition: associativity")
        var v4 = v1
        v4 += v2
        assertEquals(v1 + v2, v4, "addition: assign")
    }

    /* Checks properties of subtraction and assignment*/
    @Test
    internal fun minus() {
        assertEquals(Vector2d(1.0, -1.0), v1 - v2, "subtraction")
        assertEquals(v1, v1 - zero, "'-' neutral ")
        assertEquals(-v1, zero - v1, "neutral '-'")
        var v4 = v1
        v4 -= v2
        assertEquals(v1 - v2, v4, "subtraction: assign")
    }

    /* Checks unary minus operator */
    @Test
    internal fun unaryMinus() {
        assertEquals((-1.0) * v, -v, "unaryMinus")
        assertEquals(v, -(-v), "unaryMinus")
    }

    /* Checks properties of scaling */
    @Test
    internal fun scaling() {
        assertEquals(Vector2d(4.0, 6.0), 2.0 * v, "scaling")
        assertEquals(zero, v * 0.0, "scaling absorbant")
        assertEquals(zero, 0.0 * v, "absorbant scaling")
        assertEquals(2.0 * v, v * 2.0, "scaling: commutativity")
    }

    /* Checks properties of scalar product */
    @Test
    internal fun scalarProduct() {
        assertEquals(26.0, v1 * v2, delta, "scalar product")
        assertEquals(v1 * v2, v2 * v1, delta, "scalar product: commutativity")
        assertEquals(0.0, zero * v1, delta, "absorbant '*'")
        assertEquals(0.0, Vector2d(2.0, 2.0) * Vector2d(-5.0, 5.0), delta, "Perpendicular vectors")
    }

    /* Checks properties of magnitude */
    @Test
    internal fun magnitude() {
        assertEquals(sqrt(2.0 * 2.0 + 3.0 * 3.0), mag(v), delta, "magnitude")
        assertEquals(0.0, mag(zero), delta, "zero magnitude")
    }

}
