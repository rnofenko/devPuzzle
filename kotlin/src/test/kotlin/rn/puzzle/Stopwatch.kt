package rn.puzzle

class Stopwatch {
    private var startTime = 0L

    init {
        start()
    }

    fun start() {
        startTime = System.nanoTime()
    }

    fun show(message: String = "") {
        val elapsedTime = Math.round((System.nanoTime() - startTime) / 1_000_000_0.0) / 100.0
        System.out.println("$message time=$elapsedTime")
    }

    companion object {
        fun start(): Stopwatch {
            val w = Stopwatch()
            w.start()
            return w
        }
    }
}