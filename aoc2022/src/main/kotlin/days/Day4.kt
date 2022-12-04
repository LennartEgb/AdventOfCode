package days

import Day

object Day4 : Day {
    override fun part1(input: List<String>): Any = input.count { pairs ->
        val (a, b) = pairs.createPair()
        a.all { it in b } || b.all { it in a }
    }

    override fun part2(input: List<String>): Any = input.count { pairs ->
        val (a, b) = pairs.createPair()
        a.any { it in b }
    }

    private fun String.createPair(): Pair<IntRange, IntRange> = split(",").map { elf ->
        elf.split("-").let { (it.first().toInt()..it.last().toInt()) }
    }.let { it.first() to it.last() }
}