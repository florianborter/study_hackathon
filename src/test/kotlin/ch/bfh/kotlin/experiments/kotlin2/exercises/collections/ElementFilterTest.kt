package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigInteger
import java.util.stream.Stream


internal class ElementFilterTest {

    @Test
    fun addElement() {
        val filter = ElementFilter<String>()
        assertTrue(filter.addElement("Foo"))
        assertFalse(filter.addElement("Foo"))
        val filter2 = ElementFilter("s1", "s2", "s3")
        assertTrue(filter2.addElement("s4"))
        assertFalse(filter2.addElement("s3"))
    }

    @Test
    fun removeElement() {
        val filter = ElementFilter("Foo")
        assertTrue(filter.removeElement("Foo"))
        assertFalse(filter.removeElement("Foo"))
    }

    @Test
    fun filterList() {
        val filter = ElementFilter<Int>()
        val inputList = listOf(1, 2, 3, 2, 2, 3, 4, 1)
        Assertions.assertEquals(listOf<Int>(), filter.filterList(inputList))
        filter.addElement(1)
        Assertions.assertEquals(listOf(1, 1), filter.filterList(inputList))
        filter.addElement(2)
        Assertions.assertEquals(listOf(1, 2, 2, 2, 1), filter.filterList(inputList))
        filter.addElement(3)
        Assertions.assertEquals(listOf(1, 2, 3, 2, 2, 3, 1), filter.filterList(inputList))
        filter.addElement(4)
        Assertions.assertEquals(listOf(1, 2, 3, 2, 2, 3, 4, 1), filter.filterList(inputList))
        filter.addElement(5)
        Assertions.assertEquals(listOf(1, 2, 3, 2, 2, 3, 4, 1), filter.filterList(inputList))
        filter.removeElement(2)
        Assertions.assertEquals(listOf(1, 3, 3, 4, 1), filter.filterList(inputList))
    }

    @Test
    fun testFilterStreamOnBigInteger() {
        val filter = ElementFilter<BigInteger>(BigInteger.valueOf(4))
        val input = Stream.of(
            BigInteger.valueOf(1), BigInteger.valueOf(4),
            BigInteger.valueOf(2), BigInteger.valueOf(1), BigInteger.valueOf(3), BigInteger.valueOf(4)
        )
        val output = filter.filterStream(input)
        Assertions.assertEquals(listOf(BigInteger.valueOf(4), BigInteger.valueOf(4)), output.toList())
    }

    @Test
    fun filterStringLists() {
        val filter = ElementFilter("Foo", "Bar", "Foo")
        assertFalse(filter.addElement("Foo"))
        assertTrue(filter.addElement("Bla"))
        val stream = Stream.of("Foo", "Baz", "Bla")
        Assertions.assertEquals(
            listOf("Foo", "Bla"),
            filter.filterStream(stream).toList()
        )
        Assertions.assertEquals(listOf("Bar", "Bla", "Foo"), filter.elements)
    }

    @Test
    fun getUniqueElements() {
        val filter = ElementFilter(1, 2, 3, 2, 2, 3, 4, 1)
        Assertions.assertEquals(listOf(1, 2, 3, 4), filter.elements)
    }

    @Test
    fun reset() {
        val filter = ElementFilter<String>()
        assertTrue(filter.addElement("Foo"))
        assertFalse(filter.addElement("Foo"))
        filter.reset() // clear filter
        assertTrue(filter.addElement("Foo"))
    }
}