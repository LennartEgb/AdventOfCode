package days

import Day

object Day6: Day {
    override fun part1(input: List<String>): Any = input.map { line -> line.indexOfFirstUnique(n = 4) }
    override fun part2(input: List<String>): Any = input.map { line -> line.indexOfFirstUnique(n = 14) }
    private fun String.indexOfFirstUnique(n: Int): Int = windowedSequence(n).indexOfFirst { it.toSet().size == n } + n
}