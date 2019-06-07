package rn.puzzle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PuzzleApplication

fun main(args: Array<String>) {
	runApplication<PuzzleApplication>(*args)
}
