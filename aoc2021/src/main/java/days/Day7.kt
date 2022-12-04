package days

import Day
import kotlin.math.abs

object Day7 : Day {
    override fun part1(input: List<String>): Any {
        val positions = input.parse()
        val range = (positions.min()..positions.max())
        val fuelToPosition = range.map { position -> positions.sumOf { abs(it - position) } }
        return fuelToPosition.min()
    }

    override fun part2(input: List<String>): Any {
        val positions = input.parse()
        val range = positions.min()..positions.max()
        val fuelToPosition = range.map { position -> positions.sumOf { it.getFuel(to = position) } }
        return fuelToPosition.min()
    }

    private fun List<String>.parse(): List<Int> = single().split(",").map { it.toInt() }
    private fun Int.getFuel(to: Int): Int = (0..abs(this - to)).sum()
}


