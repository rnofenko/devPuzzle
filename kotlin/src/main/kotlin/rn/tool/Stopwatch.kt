package rn.tool

class Stopwatch {
    private var startTime = 0L

    init {
        start()
    }

    fun start(): Stopwatch {
        startTime = System.nanoTime()
        return this
    }

    fun show(message: String = ""): Stopwatch {
        val elapsedTime = Math.round((System.nanoTime() - startTime) / 1_000_000_0.0) / 100.0
        println("$message time=$elapsedTime")
        return this
    }

    companion object {
        fun start(): Stopwatch {
            val w = Stopwatch()
            w.start()
            return w
        }
    }
}