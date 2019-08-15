package rn.puzzle.tool

import java.text.NumberFormat
import java.util.*

object Formatter {
    fun format(n: Long): String {
        val loc = NumberFormat.getNumberInstance(Locale.US)
        return loc.format(n).padStart(26, ' ')
    }
}