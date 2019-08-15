package rn.standard.string.suffix.array

interface ISuffixArrayBuilder {
    fun build(text: String): IntArray
}