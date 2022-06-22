package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

/** Programming 2 with Kotlin - FS 21/22,
 *  Computer Science, Bern University of Applied Sciences */

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ArrayListSetTest {
    companion object {
        const val zero = 0
        const val one = 1
        const val two = 2
        const val three = 3
        const val four = 4
        const val five = 5
        const val six = 6
        const val seven = 7
        const val eight = 8
        const val nine = 9
        const val m1 = -1
        const val m2 = -2
        const val m3 = -3
        const val m4 = -4
        const val m5 = -5
        const val m6 = -6
        const val m10 = -10

        /**
         * The number of elements to place in collections, arrays, etc.
         */
        const val SIZE = 20
    }

    /**
     * Returns a new set of given size containing consecutive Integers 0 ... n.
     */
    private fun populatedSet(n: Int): ArrayListSet<Int> {
        val q = ArrayListSet<Int>()
        assertTrue(q.isEmpty())
        run {
            var i = n - 1
            while (i >= 0) {
                assertTrue(q.add(i))
                i -= 2
            }
        }
        var i = n and 1
        while (i < n) {
            assertTrue(q.add(i))
            i += 2
        }
        assertFalse(q.isEmpty())
        assertEquals(n, q.size)
        return q
    }

    /**
     * Returns a new set of first 5 ints.
     */
    @Test
    fun set5() {
        val q = ArrayListSet<Int>()
        assertTrue(q.isEmpty())
        q.add(one)
        q.add(two)
        q.add(three)
        q.add(four)
        q.add(five)
        assertEquals(5, q.size)
    }

    @Test
    fun testRetainAll() {
        val s1 = ArrayListSet<Int>()
        s1.add(1)
        s1.add(2)
        s1.add(3)
        s1.add(4)
        s1.add(5)
        val s2 = ArrayListSet<Int>()
        s2.add(3)
        s2.add(4)
        s2.add(5)
        s2.add(6)
        s2.add(7)
        s1.retainAll(s2)
        assertTrue(s1.contains(3))
        assertTrue(s1.contains(4))
        assertTrue(s1.contains(5))
        assertFalse(s1.contains(1))
        assertFalse(s1.contains(2))
        assertFalse(s1.contains(6))
        assertFalse(s1.contains(7))
    }

    /**
     * A new set has unbounded capacity
     */
    @Test
    fun testConstructor1() {
        assertEquals(0, ArrayListSet<Int>().size)
    }

    /**
     * Set contains all elements of collection used to initialize
     */
    @Test
    fun testConstructor6() {
        val ints = ArrayList<Int>(SIZE)
        for (i in 0..SIZE) ints.add(i)
        val q = ArrayListSet(ints)
        for (i in 0..SIZE) assertTrue(q.contains(ints[i]))
    }

    /**
     * isEmpty is true before add, false after
     */
    @Test
    fun testEmpty() {
        val q = ArrayListSet<Int>()
        assertTrue(q.isEmpty())
        q.add(1)
        assertFalse(q.isEmpty())
        q.add(2)
        q.remove(1)
        q.remove(2)
        assertTrue(q.isEmpty())
    }

    /**
     * size changes when elements added and removed
     */
    @Test
    fun testSize() {
        val q = populatedSet(SIZE)
        for (i in 0 until SIZE) {
            assertEquals(SIZE - i, q.size)
            q.remove(i)
        }
        for (i in 0 until SIZE) {
            assertEquals(i, q.size)
            q.add(i)
        }
    }

    /**
     * Add of comparable element succeeds
     */
    @Test
    fun testAdd() {
        val q = ArrayListSet<Int>()
        assertTrue(q.add(zero))
        assertTrue(q.add(one))
    }

    /**
     * Add of duplicate element fails
     */
    @Test
    fun testAddDup() {
        val q = ArrayListSet<Int>()
        assertTrue(q.add(zero))
        assertFalse(q.add(zero))
    }

    /**
     * remove(x) removes x and returns true if present
     */
    @Test
    fun testRemoveElement() {
        val q = populatedSet(SIZE)
        run {
            var i = 1
            while (i < SIZE) {
                assertTrue(q.contains(i))
                assertTrue(q.remove(i))
                assertFalse(q.contains(i))
                assertTrue(q.contains(i - 1))
                i += 2
            }
        }
        var i = 0
        while (i < SIZE) {
            assertTrue(q.contains(i))
            assertTrue(q.remove(i))
            assertFalse(q.contains(i))
            assertFalse(q.remove(i + 1))
            assertFalse(q.contains(i + 1))
            i += 2
        }
        assertTrue(q.isEmpty())
    }

    /**
     * contains(x) reports true when elements added but not yet removed
     */
    @Test
    fun testContains() {
        val q = populatedSet(SIZE)
        for (i in 0 until SIZE) {
            assertTrue(q.contains(i))
            q.remove(i)
            assertFalse(q.contains(i))
        }
    }

    /**
     * clear removes all elements
     */
    @Test
    fun testClear() {
        val q = populatedSet(SIZE)
        q.clear()
        assertTrue(q.isEmpty())
        assertEquals(0, q.size)
        q.add(1)
        assertFalse(q.isEmpty())
        q.clear()
        assertTrue(q.isEmpty())
    }

    /**
     * containsAll(c) is true when c contains a subset of elements
     */
    @Test
    fun testContainsAll() {
        val q = populatedSet(SIZE)
        val p = ArrayListSet<Int?>()
        for (i in 0 until SIZE) {
            assertTrue(q.containsAll(p))
            assertFalse(p.containsAll(q))
            p.add(i)
        }
        assertTrue(p.containsAll(q))
    }

    /**
     * removeAll(c) removes only those elements of c and reports true if changed
     */
    @Test
    fun testRemoveAll() {
        for (i in 1 until SIZE) {
            val q = populatedSet(SIZE)
            val p = populatedSet(i)
            assertTrue(q.removeAll(p))
            assertEquals(SIZE - i, q.size)
        }
    }

    /**
     * toArray contains all elements in sorted order
     */
    @Test
    fun testToArray() {
        val q = populatedSet(SIZE)
        val o = q.toArray()
        for (i in o.indices) assertTrue(q.contains(o[i]))
    }

    /**
     * iterator iterates through all elements
     */
    @Test
    fun testIterator() {
        val q = populatedSet(SIZE)
        val it: Iterator<Int?> = q.iterator()
        var i = 0
        while (it.hasNext()) {
            assertTrue(q.contains(it.next()))
            i++
        }
        assertEquals(i, SIZE)
    }

    /**
     * toString contains toStrings of elements
     */
    @Test
    fun testToString() {
        val q = populatedSet(SIZE)
        val s = q.toString()
        for (i in 0 until SIZE) {
            assertTrue(s.contains(i.toString()))
        }
    }

    /**
     * addAll is idempotent
     */
    @Test
    @Throws(Exception::class)
    fun testAddAll_idempotent() {
        val x = populatedSet(SIZE)
        val y = ArrayListSet(x)
        y.addAll(x)
        assertEquals(x, y)
        assertEquals(y, x)
    }

    @Test
    fun testEquals() {
        val ints = intArrayOf(1, 2, 3, 4)
        val l1 = ArrayListSet<Int>()
        val l2 = ArrayListSet<Int>()

        ints.forEachIndexed { idx, i ->
            l1.addAll(ints.toList())
            l2.addAll(ints.toList().reversed())
        }
        assertEquals(l1, l2)
        assertEquals(l2, l1)
    }


    /* Equality correspond to a equivalence relation that has three properties:
     * reflexivity, symmetry, transitivity.
     * We should tests these properties, actually reflexivity cannot induce errors and could be ignored.
     * It is also important to check the property related to the hashCode() member function, i.e.
     * if x equals y then x.hashCode() equals y.hashCode()
     */

    /**
     * Checks symmetry  for all l1, l2 l1 R l2 => l2 R l1
     * x R y means x is in relation (relation R) with y
     */
    @Test
    fun testEqualsSymmetry() {
        val l1 = ArrayListSet<Int>(listOf(1, 2, 3, 4))
        val l2 = ArrayListSet<Int>(listOf(4, 3, 2, 1))

        assertEquals(l1, l2)
        assertEquals(l2, l1)
    }

    /**
     * Checks transitivity l1 R l2 and l2 R l3 => l1 R l3
     */
    @Test
    fun testEqualsTransitivity() {
        val l1 = ArrayListSet<Int>(listOf(1, 2, 3, 4))
        val l2 = ArrayListSet<Int>(listOf(4, 3, 2, 1))
        val l3 = ArrayListSet<Int>(listOf(2, 3, 4, 1))

        assertEquals(l1, l2)
        assertEquals(l2, l3)
        assertEquals(l1, l3)
    }

    /**
     * Checks hashCode property with respect of equals
     * if x == y then x.hashCode() == y.hashCode()
     */
    @Test
    fun testHashCode() {
        val ints = listOf(1, 2, 3, 4)
        val l1 = ArrayListSet<Int>(ints)
        val l2 = ArrayListSet<Int>(ints)

        assertEquals(l1, l2)
        assertEquals(l1.hashCode(), l2.hashCode())
    }


    /**
     * Checks inequality with empty structure
     */
    @Test
    fun testNotEqualsEmtpy() {
        val l1 = ArrayListSet<Int>(listOf(1, 2, 3, 4))
        val l2 = ArrayListSet<Int>()

        assertNotEquals(l1, l2)
    }

    /**
     * Checks inequality with non empty structures
     */
    @Test
    fun testNotEquals() {
        val l1 = ArrayListSet<Int>(listOf(1, 2, 3, 4))
        val l2 = ArrayListSet<Int>(listOf(1, 4, 2))

        assertNotEquals(l1, l2)
    }
}