package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

class ArrayListSet<T>() : Set<T> {
    val internalElements = ArrayList<T>()

    constructor(c: Collection<T>) : this() {
        internalElements.addAll(c)
    }

    override val size: Int
        get() = internalElements.size

    override fun contains(element: T): Boolean = internalElements.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = internalElements.containsAll(elements)

    override fun isEmpty(): Boolean = internalElements.isEmpty()

    override fun iterator(): Iterator<T> = internalElements.distinct().iterator()

    fun add(element: T): Boolean {
        if (!internalElements.contains(element)) {
            return internalElements.add(element)
        }
        return false
    }

    fun retainAll(elements: ArrayListSet<T>): Boolean = internalElements.retainAll(elements)

    fun remove(element: T): Boolean = internalElements.remove(element)

    fun clear() = internalElements.clear()

    fun removeAll(elements: ArrayListSet<T>): Boolean = internalElements.removeAll(elements)

    fun addAll(elements: Collection<T>) = elements.forEach { add(it) }

    fun toArray() = internalElements.toArray() as Array<*>

    override fun toString(): String = "ArrayListSet$internalElements"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArrayListSet<*>

        internalElements.forEach {
            if (!other.contains(it)) return false
        }

        return true
    }

    override fun hashCode(): Int = internalElements.hashCode()


}