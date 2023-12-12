package days

import Day

object Day4 : Day {

    data class ScratchCard(val winning: List<Int>, val numbers: List<Int>) {
        val matchingNumbers = numbers.count { it in winning }
        fun getValue(): Int = if (matchingNumbers == 0) 0 else (1..matchingNumbers).reduce { acc, _ -> acc * 2 }
    }

    private val numberRegex = """\d+""".toRegex()
    private fun String.toValues(): List<Int> = numberRegex.findAll(this).map { it.value.toInt() }.toList()
    private fun Array<Int>.update(index: Int, by: (Int) -> Int) = set(index, by(get(index)))

    override fun part1(input: List<String>): Any = extractScratchCards(input).sumOf(ScratchCard::getValue)
    override fun part2(input: List<String>): Any {
        // NOTE: create an array with ones for one copy each
        val copies = Array(input.size) { 1 }
        val scratchCards = extractScratchCards(input)
        val values = scratchCards.map { it.matchingNumbers }

        for ((index, value) in values.withIndex()) {
            repeat(copies[index]) {
                (1..value).forEach { step -> copies.update(index = index + step) { it + 1 } }
            }
        }

        return copies.sumOf { it }
    }

    private fun extractScratchCards(lines: List<String>): List<ScratchCard> = lines.map { line ->
        val (winning, numbers) = line.substringAfter(":").split("|")
        ScratchCard(winning = winning.toValues(), numbers = numbers.toValues())
    }
}
