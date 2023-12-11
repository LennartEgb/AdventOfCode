package days

import Day

private data class IntPair(val first: Int, val second: Int) {
    override fun toString(): String = "($first, $second)"
}

private infix fun Int.to(other: Int): IntPair = IntPair(this, other)

// It's not: 542418
object Day3 : Day {
    private val NUMBER = """\d+""".toRegex()
    private fun Char.isSymbol(): Boolean = this != '.' && !isWhitespace() && !isDigit()

    private fun parts(input: List<String>): Map<IntPair, List<Int>> = mutableMapOf<IntPair, MutableList<Int>>().apply {
        for ((y, line) in input.withIndex()) {
            for (match in NUMBER.findAll(line)) {
                val number = match.value.toInt()
                for (y2 in (y - 1).coerceAtLeast(0)..(y + 1).coerceAtMost(input.lastIndex)) {
                    val line2 = input[y2]
                    val x0 = match.range.first - 1
                    val x1 = match.range.last + 1
                    for (x in x0.coerceAtLeast(0)..x1.coerceAtMost(line2.lastIndex)) {
                        if (line2[x].isSymbol()) {
                            getOrPut(x to y2) { mutableListOf() } += number
                        }
                    }
                }
            }
        }
    }

    override fun part1(input: List<String>): Any = parts(input).values.sumOf { it.sum() }
    override fun part2(input: List<String>): Any = parts(input).values.sumOf { if (it.size == 2) it[0] * it[1] else 0 }
}

