package ch.bfh.kotlin.experiments.kotlin2.exercises.operatoroverloading


/** Programming 2 with Kotlin - FS 19/20,
 *  Computer Science, Bern University of Applied Sciences */

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexNumber(val real: Double, val img: Double) {
    override fun equals(other: Any?): Boolean {
        if(other is ComplexNumber) {
            return this.real == other.real && this.img == other.img
        }

        return false
    }

    override fun hashCode(): Int {
        var result = real.hashCode()
        result = 31 * result + img.hashCode()
        return result
    }
}

operator fun ComplexNumber.plus(other: ComplexNumber) =
    ComplexNumber(this.real + other.real, this.img + other.img)

operator fun ComplexNumber.plus(other: Double) =
    ComplexNumber(this.real + other, this.img)

operator fun ComplexNumber.minus(other: ComplexNumber) =
    ComplexNumber(this.real - other.real, this.img - other.img)

operator fun ComplexNumber.minus(other: Double) =
    ComplexNumber(this.real - other, this.img)

operator fun ComplexNumber.times(other: ComplexNumber) =
    ComplexNumber(this.real * other.real - this.img * other.img, this.img * other.real + this.real * other.img)

operator fun ComplexNumber.times(other: Double) =
    ComplexNumber(other, 0.0) * this

operator fun Double.minus(other: ComplexNumber) =
    ComplexNumber(this - other.real, 0 - other.img)

operator fun Double.plus(other: ComplexNumber) =
    ComplexNumber(this + other.real, other.img)

operator fun Double.times(other: ComplexNumber) =
    ComplexNumber(this, 0.0) * other


internal class ComplexNumberTest {

    @Test
    fun plus() {
        val c1 = ComplexNumber(5.5, 4.0)
        val c2 = ComplexNumber(1.2, 3.5)
        assertEquals(ComplexNumber(6.7, 7.5), c1 + c2)
        assertEquals(c1 + c2, c2 + c1)
        var c3 = ComplexNumber(2.4, 7.2)
        assertEquals(c1 + c2 + c3, c3 + c2 + c1)
        c3 += c1
        assertEquals(c1 + ComplexNumber(2.4, 7.2), c3)
    }

    @Test
    fun plusDouble() {
        val c1 = ComplexNumber(5.5, 4.0)
        assertEquals(ComplexNumber(9.5, c1.img), c1 + 4.0)
        assertEquals(ComplexNumber(9.5, c1.img), 4.0 + c1)
        assertEquals(c1 + 3.0, 3.0 + c1)
    }

    @Test
    fun minus() {
        val c1 = ComplexNumber(5.5, 4.0)
        val c2 = ComplexNumber(1.2, 3.5)
        assertEquals(ComplexNumber(4.3, 0.5), c1 - c2)
        var c3 = ComplexNumber(2.4, 7.2)
        c3 -= c1
        assertEquals(ComplexNumber(2.4, 7.2) - c1, c3)
    }

    @Test
    fun minusDouble() {
        val c1 = ComplexNumber(5.5, 4.0)
        assertEquals(ComplexNumber(1.5, c1.img), c1 - 4.0)
        assertEquals(ComplexNumber(-1.5, c1.img * -1.0), 4.0 - c1)
    }

    @Test
    fun times() {
        val m1 = ComplexNumber(5.0, -2.0)
        val m2 = ComplexNumber(3.0, 4.0)
        assertEquals(ComplexNumber(23.0, 14.0), m1 * m2)
        assertEquals(m1 * m2, m2 * m1)
        var m3 = ComplexNumber(5.5, 4.0)
        assertEquals(m1 * m2 * m3, m3 * m2 * m1)
        m3 *= m1
        assertEquals(m1 * ComplexNumber(5.5, 4.0), m3)
    }

    @Test
    fun timesDouble() {
        val c1 = ComplexNumber(5.5, 4.0)
        assertEquals(ComplexNumber(22.0, 16.0), c1 * 4.0)
        assertEquals(ComplexNumber(22.0, 16.0), 4.0 * c1)
        assertEquals(c1 * 3.0, 3.0 * c1)
    }
}