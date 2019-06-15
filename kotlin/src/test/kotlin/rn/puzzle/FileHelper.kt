package rn.puzzle

import java.io.File

object FileHelper {
    fun load(path: String): List<String> {
        return File(path).readLines()
    }
}