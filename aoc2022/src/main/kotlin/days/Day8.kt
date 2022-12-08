package days

import Day

private typealias Grid = Array<IntArray>

object Day8 : Day {
    class Forest(private val grid: Grid) {
        companion object {
            fun of(input: List<String>): Forest = input.map { row -> row.map { c -> c.digitToInt() }.toIntArray() }
                .toTypedArray().let { Forest(it) }
        }

        fun countVisible(): Int {
            var count = 0
            grid.forEach { x, y, height ->
                val (left, right) = grid.rowFor(y).partition(x)
                val (top, bottom) = grid.columnFor(x).partition(index = y)
                if (left.verify(height) || right.verify(height) || top.verify(height) || bottom.verify(height)) {
                    count++
                }
            }
            return count
        }

        fun getHighestScenicScore(): Int {
            var highest = 0
            grid.forEach { x, y, height ->
                val (top, bottom) = grid.columnFor(x).partition(y)
                val (left, right) = grid.rowFor(y).partition(x)
                val score = top.reversed().scoreFor(height) *
                        right.scoreFor(height) *
                        bottom.scoreFor(height) *
                        left.reversed().scoreFor(height)
                if (score > highest) highest = score
            }
            return highest
        }

        private fun List<Int>.scoreFor(height: Int): Int {
            val index = indexOfFirst { it >= height }
            if (index == -1 && isNotEmpty()) return size
            return index + 1
        }

        private fun List<Int>.verify(height: Int): Boolean = isEmpty() || (all { it < height })
        private fun List<Int>.partition(index: Int): Pair<List<Int>, List<Int>> =
            subList(fromIndex = 0, toIndex = index) to subList(fromIndex = index + 1, toIndex = size)

        private fun Grid.columnFor(x: Int): List<Int> = map { it[x] }
        private fun Grid.rowFor(y: Int): List<Int> = get(y).toList()
        private inline fun Grid.forEach(action: (x: Int, y: Int, height: Int) -> Unit) = forEachIndexed { y, row ->
            row.forEachIndexed { x, height -> action(x, y, height) }
        }
    }

    override fun part1(input: List<String>): Any = Forest.of(input).countVisible()
    override fun part2(input: List<String>): Any = Forest.of(input).getHighestScenicScore()
}