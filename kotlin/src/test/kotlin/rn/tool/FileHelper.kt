package rn.tool

import java.io.File

object FileHelper {
    @JvmStatic
    fun load(path: String): List<String> {
        return File(path).readLines()
    }

    fun loadByInputNo(no: Int): List<String> {
        return loadByNo("input", no)
    }

    fun loadByOutputNo(no: Int): List<String> {
        return loadByNo("output", no)
    }

    private fun loadByNo(inOutPrefix: String, no: Int): List<String> {
        val prefix = "/Users/tkma0vh/Downloads/"
        val strNo = no.toString().padStart(2, '0')
        val path = "$prefix$inOutPrefix$strNo.txt"
        return File(path).readLines()
    }
}