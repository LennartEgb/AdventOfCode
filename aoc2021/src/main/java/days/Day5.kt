package days

import Day

object Day5 : Day {
    override fun part1(input: List<String>): Any {
        var width = 0
        var height = 0
        val lines = input.map {
            val (start, end) = it.split(" -> ")
            val line = Point(start) + Point(end)
            width = maxOf(line.start.x, line.end.x, width)
            height = maxOf(line.start.y, line.end.y, height)
            line
        }

        val board = Board(width = width, height = height)
        lines.forEach { line ->
            if (line.isDiagonal) return@forEach
            (line.start..line.end).forEach { point -> board.mark(point) }
        }
        return board.getHigherThan(1)
    }

    override fun part2(input: List<String>): Any {
        var width = 0
        var height = 0
        val lines = input.map {
            val (start, end) = it.split(" -> ")
            val line = Point(start) + Point(end)
            width = maxOf(line.start.x, line.end.x, width)
            height = maxOf(line.start.y, line.end.y, height)
            line
        }

        val board = Board(width = width, height = height)
        lines.forEach { line ->
            (line.start..line.end).forEach { point ->
                board.mark(point)
            }
        }
        return board.getHigherThan(1)
    }

    class Board private constructor(private val array: Array<Array<Int>>) {
        constructor(width: Int, height: Int) : this(Array(height + 1) { Array(width + 1) { 0 } })

        fun mark(point: Point) {
            array[point.y][point.x] = array[point.y][point.x] + 1
        }

        fun getHigherThan(n: Int): Int = array.sumOf { rows -> rows.count { it > n } }

        override fun toString(): String {
            return buildString { array.forEach { rows -> appendLine(rows.joinToString("") { if (it == 0) "." else it.toString() }) } }
        }
    }

    data class Line(val start: Point, val end: Point) {
        val isDiagonal: Boolean = start.x != end.x && start.y != end.y
    }

    data class Point(val x: Int, val y: Int) {
        constructor(string: String) : this(x = string.split(",").first().toInt(), y = string.split(",").last().toInt())

        operator fun rangeTo(point: Point): List<Point> {
            return when {
                x == point.x -> (minOf(y, point.y)..maxOf(y, point.y)).map { copy(x = x, y = it) }
                y == point.y -> (minOf(x, point.x)..maxOf(x, point.x)).map { copy(x = it, y = y) }
                else -> {
                    val ys = if (y < point.y) (y..point.y).toList() else (y downTo point.y).toList()
                    val xs = if (x < point.x) (x..point.x).toList() else (x downTo point.x).toList()
                    xs.mapIndexed { index, x -> Point(x = x, y = ys[index]) }
                }
            }
        }

        operator fun plus(point: Point): Line = Line(start = this, end = point)
    }
}