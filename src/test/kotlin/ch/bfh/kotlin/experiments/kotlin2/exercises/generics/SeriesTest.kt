package ch.bfh.kotlin.experiments.kotlin2.exercises.generics

fun main() {
    val s1 = Series(0, { x: Int -> x + 1 }) { x: Int -> x <= 10 }
    val s2 = Series(0, { x: Int -> x + 2 }) { x: Int -> x <= 10 }
    val s3 = Series(1, { x: Int -> x + x }) { x -> x <= 80 }
    val s4 = Series("", { s: String -> "$s*" }) { s: String -> s.length <= 10 }
    val s5 = Series("HelloWorld", { s: String -> s.substring(0, s.length - 1) }
    ) { s: String -> s != "" }

    while (s1.hasNext()) {
        print(s1.next().toString() + " ")
    }
    println()

    for ((idx, series) in arrayOf<Series<*>>(s1, s2, s3, s4, s5).withIndex()) {
        print("s$idx = ")
        println(series.toList())
    }
}
