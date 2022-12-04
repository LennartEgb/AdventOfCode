package days

import Day

object Day9 : Day {
    override fun part1(input: List<String>): Any {
        val map = Map.of(input)
        return map.getSumOfPoints(map.findLowestPoints())
    }

    // Solution from https://todd.ginsberg.com/post/advent-of-code/2021/day9/#d9p2
    override fun part2(input: List<String>): Any {
        val map = Map.of(input)
        return map.findLowestPoints()
            .map { map.getBasinSize(it) }
            .sortedDescending()
            .take(3)
            .reduce { acc, i -> acc * i }
    }

    data class Point(val x: Int, val y: Int)

    class Map private constructor(private val array: Array<IntArray>) {
        companion object {
            fun of(input: List<String>): Map =
                Map(Array(input.size) { y -> input[y].map { it.digitToInt() }.toIntArray() })
        }

        fun getBasinSize(point: Point): Int {
            val visited = mutableSetOf(point)
            val queue = mutableListOf(point)
            while (queue.isNotEmpty()) {
                val newNeighbors = queue.removeFirst()
                    .validNeighbors()
                    .filter { it !in visited }
                    .filter { array[it] != 9 }
                visited.addAll(newNeighbors)
                queue.addAll(newNeighbors)
            }
            return visited.size
        }

        fun findLowestPoints(): List<Point> {
            val lowestPoints = mutableListOf<Point>()
            array.forEachIndexed { y, row ->
                row.forEachIndexed { x, height ->
                    Point(x = x, y = y).takeIf { point ->
                        point.validNeighbors().map { array[it] }.all { height < it }
                    }?.also { lowestPoints.add(it) }
                }
            }
            return lowestPoints
        }

        fun getSumOfPoints(points: List<Point>): Int = points.sumOf { array[it.y][it.x] + 1 }

        private fun Point.validNeighbors(): List<Point> = neighbors().filter { it in array }

        private fun Point.neighbors(): List<Point> = listOf(
            Point(x = x, y = y - 1),
            Point(x = x - 1, y = y),
            Point(x = x + 1, y = y),
            Point(x = x, y = y + 1),
        )

        private operator fun Array<IntArray>.contains(point: Point): Boolean {
            return point.y in this.indices && point.x in this[point.y].indices
        }

        private operator fun Array<IntArray>.get(point: Point): Int = this[point.y][point.x]

        override fun toString(): String = buildString {
            array.forEach { row ->
                row.forEach { append(it) }
                appendLine()
            }
        }
    }
}