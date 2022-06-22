package ch.bfh.kotlin.experiments.kotlin2.exercises.collections

import java.util.stream.Stream

class ElementFilter<T>(vararg initialElements: T) {
    var elements = mutableListOf<T>()
        private set

    init {
        initialElements.forEach { addElement(it) }
    }

    fun addElement(element: T): Boolean {
        if (!elements.contains(element)) {
            return elements.add(element)
        }
        return false
    }

    fun removeElement(element: T): Boolean = elements.remove(element)

    fun reset() {
        elements = ArrayList()
    }

    fun filterList(list: List<T>): List<T> {
        val tmp = elements.toList().toMutableList()
        tmp.retainAll(list)
        return tmp
    }

    fun filterStream(stream: Stream<T>): Stream<T> {
        return filterList(stream.toList()).stream()
    }
}