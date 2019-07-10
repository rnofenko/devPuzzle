package rn.puzzle.game.easy

import org.junit.Assert
import org.junit.Test

class TowerBreakersTests {

    @Test
    fun sample1() {
        Assert.assertEquals(2, towerBreakers(2,2))
        Assert.assertEquals(1, towerBreakers(1,4))
    }

    private fun towerBreakers(n: Int, m: Int): Int {
        if(m == 1) {
            return 2
        }
        if(n % 2 == 0) {
            return 2
        }
        return 1
    }
}