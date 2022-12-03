package days

import Day

object Day3 : Day {
    override fun part1(input: List<String>): Any {
        val gamma = input.gamma()
        val epsilon = gamma xor List(input.first().length) { 1 }.joinToString(separator = "").toInt(radix = 2)
        return gamma * epsilon
    }

    override fun part2(input: List<String>): Any {
        val oxygen = oxygenRating(input).toInt(radix = 2)
        val co2 = co2Rating(input).toInt(radix = 2)
        return oxygen * co2
    }

    private fun oxygenRating(input: List<String>): String {
        return input.rating { column, mostCommon -> this[column] == mostCommon }
    }

    private fun co2Rating(input: List<String>): String {
        return input.rating { column, mostCommon -> this[column] != mostCommon }
    }

    private fun List<String>.rating(predicate: String.(column: Int, mostCommon: Char) -> Boolean): String {
        val columns = first().indices
        var candidates = this
        for (column in columns) {
            val (zeroes, ones) = candidates.countBitsInColumn(column)
            val mostCommon = if (zeroes > ones) '0' else '1'
            candidates = candidates.filter { it.predicate(column, mostCommon) }
            if (candidates.size == 1) break
        }
        return candidates.single()
    }

    private fun List<String>.countBitsInColumn(column: Int): BitCount = fold(BitCount(zeroes = 0, ones = 0)) { acc, s ->
        if (s[column] == '0') acc.incZeroes() else acc.incOnes()
    }

    private fun List<String>.gamma(): Int {
        return first().indices.map { column -> countBitsInColumn(column) }
            .map { it.getZeroOrOne() }
            .joinToString(separator = "")
            .toInt(radix = 2)
    }

    data class BitCount(val zeroes: Int, val ones: Int) {
        fun incZeroes(): BitCount = BitCount(zeroes = zeroes + 1, ones = ones)
        fun incOnes(): BitCount = BitCount(zeroes = zeroes, ones = ones + 1)
        fun getZeroOrOne(): Int = if (zeroes > ones) 0 else 1
    }
}