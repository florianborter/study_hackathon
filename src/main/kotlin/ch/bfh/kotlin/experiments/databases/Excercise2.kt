package ch.bfh.kotlin.experiments.databases

fun main() {

}

/* val l1 = mutableListOf<Int>()
    val l2 = mutableListOf<Int>()
    l1.addAll(100000000 downTo 0)
    l2.addAll(100000000 downTo 0)
    val start = System.currentTimeMillis()
    val res = same(l1, l2)
    val end = System.currentTimeMillis()
    println("same: $res time: ${end - start}ms")*/
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

//same2 is the same as same but is slow AF
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

fun subset(arr1: BooleanArray, arr2: BooleanArray): Boolean {
    if(arr1.size > arr2.size) {
        return false
    }

    for(i in arr1.indices) {
        if(arr1[i] && !arr2[i]) {
            return false
        }
    }

    return true
}

fun union(arr1: BooleanArray, arr2: BooleanArray): BooleanArray {
    val newSize = if (arr1.size > arr2.size) arr1.size else arr2.size
    val unionArr = BooleanArray(newSize)

    for(i in 0 until newSize) {
        unionArr[i] =  arr1.getOrElse(i){false} || arr2.getOrElse(i){false}
    }
    return unionArr
}


/*val arr1 = arrayOf(booleanArrayOf(true, false, false), booleanArrayOf(false, true, false))
    val arr2 = arrayOf(booleanArrayOf(true, false, false), booleanArrayOf(true, false, true), booleanArrayOf(true, false, true, true))
    compose(arr1, arr2)*/
fun compose(arr1: Array<BooleanArray>, arr2: Array<BooleanArray>): Unit {
    for (i in arr1.indices) {
        for (j in arr1[i].indices) {
                for (k in arr2[j].indices) {
                    if (arr1[i][j] && arr2[j][k]) {
                        println("i: $i und k: $k sind durch j: $j verbunden")
                    }
                }
        }
    }
}

/*val a1 = listOf(Pair(1, 2),Pair(1, 3),Pair(2, 1),Pair(1, 4))
  val a2 = listOf(Pair(2, 5),Pair(5, 3),Pair(1, 7),Pair(1, 5))
  compose2(a1, a2)*/
fun compose2(arr1: List<Pair<Int, Int>>, arr2: List<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
    val res = mutableListOf<Pair<Int, Int>>()
    for (i in arr1.indices) {
        for (j in arr2.indices) {
            if (arr1[i].second == arr2[j].first) {
                println("composition found: (${arr1[i].first}, ${arr2[j].second})")
                res.add(Pair(arr1[i].first, arr2[j].second))
            }
        }
    }
    return res
}