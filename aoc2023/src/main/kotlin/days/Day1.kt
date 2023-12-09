package days

import Day

object Day1 : Day {
    override fun part1(input: List<String>): Any {
        return input.sumOf { it.firstInt() * 10 + it.lastInt() }
    }

    override fun part2(input: List<String>): Any {
        val digits = List(10) { "$it" }
        val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val digitsAndWords = digits + words
        return input.sumOf { line ->
            val (_, firstValue) = line.findAnyOf(digitsAndWords) ?: error("malformed line $line")
            val (_, lastValue) = line.findLastAnyOf(digitsAndWords) ?: error("malformed line $line")
            val first = words.indexOf(firstValue).takeIf { it != -1 }?.plus(1) ?: firstValue.firstInt()
            val last = words.indexOf(lastValue).takeIf { it != -1 }?.plus(1) ?: lastValue.firstInt()
            first * 10 + last
        }
    }

    private fun String.firstInt(): Int = first { it.isDigit() }.digitToInt()
    private fun String.lastInt(): Int = last { it.isDigit() }.digitToInt()
}

