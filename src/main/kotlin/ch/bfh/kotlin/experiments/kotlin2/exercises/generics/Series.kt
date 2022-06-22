package ch.bfh.kotlin.experiments.kotlin2.exercises.generics

class Series<V>(val initialValue: V, val update: (V) -> V, val condition: (V) -> Boolean) : Iterator<V> {
    private var nextValue: V = initialValue

    override fun hasNext(): Boolean {
        return condition(nextValue)
    }

    override fun next(): V {
        check(hasNext())
        val next = nextValue
        nextValue = update(nextValue)
        return next
    }

    fun reset() {
        nextValue = initialValue
    }

    fun toList(): List<V> {
        val temp = nextValue
        val result = ArrayList<V>()
        reset()
        while (hasNext()) {
            result.add(next())
        }
        nextValue = temp
        return result
    }
}