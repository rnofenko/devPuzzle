package rn.puzzle.string.easy

import java.util.*

class StrongPasswordSolver {
    fun minimumNumber(n: Int, password: String): Int {
        return minimumNumber(password.length, password)
    }

    fun minimumNumber(password: String): Int {
        val lists = Arrays.asList("0123456789", "abcdefghijklmnopqrstuvwxyz",
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "!@#$%^&*()-+")

        var specificCount = 4
        for (list in lists) {
            if(password.any { list.indexOf(it) >= 0 }) {
                specificCount--
            }
        }

        val min = 6
        if(password.length >= min) {
            return specificCount
        }

        val diff = min - password.length
        return Math.max(diff, specificCount)
    }
}