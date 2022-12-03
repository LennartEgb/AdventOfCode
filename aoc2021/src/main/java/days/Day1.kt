package days

import Day

object Day1 : Day {
    override fun part1(input: List<String>): Any {
        val values = input.map { it.toInt() }
        return getNumOfIncreases(values)
    }

    override fun part2(input: List<String>): Any {
        val values = input.map { it.toInt() }
        return values.windowed(size = 3, step = 1).map { it.sum() }
            .let { getNumOfIncreases(it) }
    }

    private fun getNumOfIncreases(values: List<Int>): Int {
        return values.foldIndexed(0) { index, acc, current ->
            val prev = values.getOrNull(index - 1) ?: Int.MAX_VALUE
            acc + if (current > prev) 1 else 0
        }
    }
}