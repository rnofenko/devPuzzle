package rn.standard.string.suffix.array

private const val S = 0
private const val L = 1

class SaIsSuffixArrayBuilder(val showLogs: Boolean) : ISuffixArrayBuilder {
    override fun build(text: String): IntArray {
        val s = convertCharsToInts(text)
        return makeSuffixArrayByInducedSorting(s, 'z' - 'a' + 1)
    }

    private fun makeSuffixArrayByInducedSorting(s: IntArray, alphabetSize: Int): IntArray {
        val typeMap = createLsMap(s)
        val bucketSizes = findBucketSizes(s, alphabetSize)

        val guessedSuffixArray = guessLmsSort(s, bucketSizes, typeMap)

        induceSortL(s, guessedSuffixArray, bucketSizes, typeMap)
        induceSortS(s, guessedSuffixArray, bucketSizes, typeMap)
        val summary = summariseSuffixArray(s, guessedSuffixArray, typeMap)
        val summarySuffixArray = makeSummarySuffixArray(summary)
        val result = accurateLmsSort(s, bucketSizes, summarySuffixArray, summary.suffixOffsets)
        induceSortL(s, result, bucketSizes, typeMap)
        induceSortS(s, result, bucketSizes, typeMap)

        showSuffixArray(result)
        return result
    }

    private fun accurateLmsSort(s: IntArray, bucketSizes: IntArray,
                                summarySuffixArray: IntArray,
                                summarySuffixOffsets: List<Int>): IntArray {
        val suffixOffsets = IntArray(s.size + 1) { -1 }
        val bucketTails = findBucketTails(bucketSizes)
        for(i in summarySuffixArray.lastIndex downTo 2) {
            val stringIndex = summarySuffixOffsets[summarySuffixArray[i]]
            val bucketIndex = s[stringIndex]
            suffixOffsets[bucketTails[bucketIndex]] = stringIndex
            bucketTails[bucketIndex] -= 1

            showSuffixArray(suffixOffsets)
        }

        suffixOffsets[0] = s.size
        showSuffixArray(suffixOffsets)
        return suffixOffsets
    }

    private fun makeSummarySuffixArray(summary: Summary): IntArray {
        val str = summary.str
        if(summary.alphabetSize == str.size) {
            val summarySuffixArray = IntArray(str.size + 1) { -1 }
            summarySuffixArray[0] = str.size

            for(x in str.indices) {
                val y = str[x]
                summarySuffixArray[y + 1] = x
            }

            return summarySuffixArray
        }

        return makeSuffixArrayByInducedSorting(summary.str, summary.alphabetSize)
    }

    private fun summariseSuffixArray(s: IntArray, guessedSuffixArray: IntArray, typeMap: IntArray): Summary {
        val lmsNames = IntArray(s.size + 1) { -1 }
        var currentName = 0

        lmsNames[guessedSuffixArray[0]] = currentName
        var lastLmsSuffixOffset = guessedSuffixArray[0]

        showSuffixArray(lmsNames)

        for(i in 1 until guessedSuffixArray.size) {
            val suffixOffset = guessedSuffixArray[i]
            if(!isLms(typeMap, suffixOffset)) {
                continue
            }

            if(!lmsSubstringsAreEqual(s, typeMap, lastLmsSuffixOffset, suffixOffset)) {
                currentName++
            }

            lastLmsSuffixOffset = suffixOffset
            lmsNames[suffixOffset] = currentName
            showSuffixArray(lmsNames)
        }

        val summarySuffixOffsets = ArrayList<Int>()
        val summaryString = ArrayList<Int>()
        for (i in lmsNames.indices) {
            if(lmsNames[i] == -1) {
                continue
            }
            summarySuffixOffsets.add(i)
            summaryString.add(lmsNames[i])
        }
        val summaryAlphabetSize = currentName + 1

        return Summary(summaryString.toIntArray(), summaryAlphabetSize, summarySuffixOffsets)
    }

    private fun convertCharsToInts(s: String): IntArray {
        return s.map { it - 'a' }.toIntArray()
    }

    private fun createLsMap(s: IntArray): IntArray {
        val map = IntArray(s.size + 1)
        map[map.size - 1] = S
        map[map.size - 2] = L

        for(i in map.size - 3 downTo 0) {
            if(s[i] == s[i + 1]) {
                map[i] = map[i + 1]
            } else {
                map[i] = if (s[i] > s[i + 1]) L else S
            }
        }
        return map
    }

    private fun isLms(map: IntArray, index: Int): Boolean {
        return index > 0 && map[index] == S && map[index - 1] == L
    }

    private fun lmsSubstringsAreEqual(s: IntArray, map: IntArray, offsetA: Int, offsetB: Int): Boolean {
        if(offsetA == s.size || offsetB == s.size) {
            return false
        }

        var i = 0
        while (true) {
            val aLms = isLms(map, i + offsetA)
            val bLms = isLms(map, i + offsetB)

            if(i > 0) {
                if(aLms != bLms) {
                    return false
                }
                if(aLms && bLms) {
                    return true
                }
            }

            if(s[i + offsetA] != s[i + offsetB]) {
                return false
            }

            i++
        }
    }

    private fun findBucketSizes(s: IntArray, alphabetSize: Int): IntArray {
        val sizes = IntArray(alphabetSize + 1)
        for (c in s) {
            sizes[c]++
        }
        return sizes
    }

    private fun findBucketHeads(sizes: IntArray): IntArray {
        var offset = 1
        val res = ArrayList<Int>()
        for (size in sizes) {
            res.add(offset)
            offset += size
        }
        return res.toIntArray()
    }

    private fun findBucketTails(sizes: IntArray): IntArray {
        var offset = 1
        val res = ArrayList<Int>()
        for (size in sizes) {
            offset += size
            res.add(offset - 1)
        }
        return res.toIntArray()
    }

    private fun guessLmsSort(s: IntArray, bucketSizes: IntArray, typeMap: IntArray): IntArray {
        val bucketTails = findBucketTails(bucketSizes)
        val guessedSuffixArray = IntArray(s.size + 1) { -1 }
        for(i in s.indices) {
            if(!isLms(typeMap, i)) {
                continue
            }
            val bucketIndex = s[i]
            guessedSuffixArray[bucketTails[bucketIndex]] = i
            bucketTails[bucketIndex] -= 1

            showSuffixArray(guessedSuffixArray)
        }

        guessedSuffixArray[0] = s.size
        showSuffixArray(guessedSuffixArray)

        return guessedSuffixArray
    }

    private fun showSuffixArray(s: IntArray, point: Int = -1) {
        if(!showLogs) {
            return
        }

        val str = s.joinToString(" ") { it.toString().padStart(2, '0') }
        println(str)
        if(point == -1) {
            return
        }
        println("   ".repeat(point) + "^^")
    }

    private fun induceSortL(s: IntArray,
                            guessedSuffixArray: IntArray, bucketSizes: IntArray,
                            typeMap: IntArray) {
        val bucketHeads = findBucketHeads(bucketSizes)
        for(i in guessedSuffixArray.indices) {
            if(guessedSuffixArray[i] == -1) {
                continue
            }
            val j = guessedSuffixArray[i] - 1
            if(j < 0 || typeMap[j] != L) {
                continue
            }

            val bucketIndex = s[j]

            guessedSuffixArray[bucketHeads[bucketIndex]] = j
            bucketHeads[bucketIndex] += 1

            showSuffixArray(guessedSuffixArray, i)
        }
    }

    private fun induceSortS(s: IntArray, guessedSuffixArray: IntArray, bucketSizes: IntArray, typeMap: IntArray) {
        val bucketTails = findBucketTails(bucketSizes)
        for(i in guessedSuffixArray.lastIndex downTo 0) {
            val j = guessedSuffixArray[i] - 1
            if(j < 0 || typeMap[j] != S) {
                continue
            }

            val bucketIndex = s[j]

            guessedSuffixArray[bucketTails[bucketIndex]] = j
            bucketTails[bucketIndex] -= 1

            showSuffixArray(guessedSuffixArray, i)
        }
    }

    private data class Summary(val str: IntArray, val alphabetSize: Int, val suffixOffsets: List<Int>)

    //https://zork.net/~st/jottings/sais.html#induced-sorting-l-type-suffixes
}