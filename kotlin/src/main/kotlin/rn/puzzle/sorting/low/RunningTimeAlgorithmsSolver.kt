package rn.puzzle.sorting.low

class RunningTimeAlgorithmsSolver {
    fun runningTime(arr: Array<Int>): Int {
        var count = 0

        for(i in 1 until arr.size) {
            val movingValue = arr[i]
            for(j in i - 1 downTo 0) {
                if(arr[j] > movingValue) {
                    arr[j + 1] = arr[j]
                    count++
                    if(j == 0) {
                        arr[0] = movingValue
                    }
                } else {
                    arr[j + 1] = movingValue
                    break
                }
            }
        }

        return count
    }
}