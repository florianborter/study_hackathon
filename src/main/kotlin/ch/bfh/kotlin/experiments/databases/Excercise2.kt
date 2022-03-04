package ch.bfh.kotlin.experiments.databases

fun main() {
    val l1 = mutableListOf<Int>()
    val l2 = mutableListOf<Int>()
    l1.addAll(100000000 downTo 0)
    l2.addAll(100000000 downTo 0)
    val start = System.currentTimeMillis()
    val res = same(l1, l2)
    val end = System.currentTimeMillis()
    println("same: $res time: ${end - start}ms")
    /*val start2 = System.currentTimeMillis()
    val res2 = same2(l1, l2)
    val end2 = System.currentTimeMillis()
    println("same2: $res2 time2: ${end2 - start2}ms")*/
}

fun same(l1: List<Int>, l2: List<Int>): Boolean {
    if (l1.size != l2.size) {
        return false
    }

    val sortedl1 = l1.sorted()
    val sortedl2 = l2.sorted()

    for (i in sortedl1.indices) {
        if (sortedl1[i] != sortedl2[i]) {
            return false
        }
    }

    return true
}

//Same2 is slow AF
fun same2(l1: MutableList<Int>, l2: MutableList<Int>): Boolean {
    if (l1.size != l2.size) {
        return false
    }

    for (el in l1) {
        if (!l2.contains(el)) {
            return false
        }
        val index = l2.indexOf(el)
        l2.removeAt(index)
    }

    return true
}