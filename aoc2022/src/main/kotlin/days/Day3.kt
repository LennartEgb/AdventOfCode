package days

import Day

object Day3 : Day {
    override fun part1(input: List<String>): Any = input.map { it.chunked(size = it.length / 2) }
        .sumOf { (a, b) -> a.first { it in b }.score }

    override fun part2(input: List<String>): Any = input.chunked(size = 3)
        .sumOf { (a, b, c) -> a.first { it in b && it in c }.score }

    private val Char.score: Int get() = if (isLowerCase()) this - 'a' + 1 else this - 'A' + 27
}
