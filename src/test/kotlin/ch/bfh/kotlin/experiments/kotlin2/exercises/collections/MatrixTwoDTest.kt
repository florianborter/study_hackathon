package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class MatrixTwoDTest {
    /*
    @Test
    fun testEmptyMatrix() {
        val empty = MatrixTwoD<Int>(arrayOf())
        val it = empty.iterator()
        assertEquals(0, empty.rows)
        assertEquals(0, empty.columns)
        assertFalse(it.hasNext())
        assertThrows(NoSuchElementException::class.java) { it.next() }
    }

    @Test
    fun testSimpleIntArray() {
        val a1 = MatrixTwoD(arrayOf(arrayOf(1, 2), arrayOf(3, 4)))
        val it1 = a1.iterator()
        assertEquals(2, a1.rows)
        assertEquals(2, a1.columns)
        assertTrue(it1.hasNext())
        var i = 1
        while (it1.hasNext()) {
            assertEquals(i, it1.next())
            i++
        }
        assertThrows(NoSuchElementException::class.java) { it1.next() }

        assertEquals(4, a1.getItem(1, 1))
        a1.setItem(1, 1, 7)
        assertEquals(7, a1.getItem(1, 1))
    }

    @Test
    fun testIntMatrix() {
        val a2 = MatrixTwoD(createMatrix(2, 3) { x: Int, y: Int -> x + y })
        val it2 = a2.iterator()
        assertEquals(2, a2.rows)
        assertEquals(3, a2.columns)
        assertTrue(it2.hasNext())
        var i = 0
        var j = 0
        while (it2.hasNext()) {
            assertEquals(i + j, it2.next())
            i++
            if (i == 3) {
                i = 0
                j++
            }
        }
        assertThrows(NoSuchElementException::class.java) { it2.next() }
    }

    @Test
    fun testStringMatrix() {
        val a3 = MatrixTwoD(createMatrix(3, 2) { x: Int, y: Int -> "$x+$y" })
        val it3 = a3.iterator()
        assertEquals(3, a3.rows)
        assertEquals(2, a3.columns)
        assertTrue(it3.hasNext())
        var i = 0
        var j = 0
        while (it3.hasNext()) {
            assertEquals("$j+$i", it3.next())
            i++
            if (i == 2) {
                i = 0
                j++
            }
        }
        assertThrows(NoSuchElementException::class.java) { it3.next() }
    }

    data class Thing(val name: String)

    @Test
    fun testThingMatrix() {
        val a3 = MatrixTwoD(
            createMatrix(4, 5) { x: Int, y: Int -> Thing("Thing${x * 10 + y}") })
        val it3 = a3.iterator()
        assertEquals(4, a3.rows)
        assertEquals(5, a3.columns)
        assertTrue(it3.hasNext())
        var i = 0
        var j = 0
        while (it3.hasNext()) {
            //println("Thing${j*10+i}")
            assertEquals(Thing("Thing${j * 10 + i}"), it3.next())
            i++
            if (i == 5) {
                i = 0
                j++
            }
        }
        assertThrows(NoSuchElementException::class.java) { it3.next() }
    }*/
}

