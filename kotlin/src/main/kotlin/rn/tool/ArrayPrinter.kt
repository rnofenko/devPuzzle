package rn.tool

object ArrayPrinter {
    fun print(a: Array<Int>) {
        println(a.joinToString(" "))
    }

    fun print(a: IntArray) {
        println(a.joinToString(" "))
    }
}