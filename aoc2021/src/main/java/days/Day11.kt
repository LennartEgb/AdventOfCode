package days

import Day

private data class Point(val x: Int, val y: Int)

private fun Point.neighbors() = listOf(
    Point(x = x - 1, y = y - 1),
    Point(x = x, y = y - 1),
    Point(x = x + 1, y = y - 1),
    Point(x = x - 1, y = y),
    Point(x = x + 1, y = y),
    Point(x = x - 1, y = y + 1),
    Point(x = x, y = y + 1),
    Point(x = x + 1, y = y + 1),
)
private typealias Cave = Map<Point, Int>

private fun cave(input: List<String>): Cave = input.flatMapIndexed { y: Int, row: String ->
    row.mapIndexed { x, value -> Point(x = x, y = y) to value.digitToInt() }
}.toMap()

private fun Cave.steps(): Sequence<Int> = sequence {
    val cave = toMutableMap()
    while (true) {
        cave.forEach { (point, value) -> cave[point] = value + 1 }
        do {
            val flashersThisRound = cave.filterValues { it > 9 }.keys
            flashersThisRound.forEach { point -> cave[point] = 0 }
            flashersThisRound.flatMap { it.neighbors() }
                .filter { it in cave && cave[it] != 0 }
                .forEach { point -> cave[point] = cave.getValue(point) + 1 }
        } while (flashersThisRound.isNotEmpty())
        yield(cave.count { it.value == 0 })
    }
}

object Day11 : Day {
    override fun part1(input: List<String>): Any = cave(input).steps().take(100).sum()
    override fun part2(input: List<String>): Any {
        val cave = cave(input)
        return cave.steps().indexOfFirst { it == cave.size } + 1
    }
}