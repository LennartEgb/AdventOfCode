package days

import Day

object Day6 : Day {
    override fun part1(input: List<String>): Any {
        return runForDays(input = input.single().split(",").map { it.toInt() }, days = 80).sum()
    }

    override fun part2(input: List<String>): Any {
        return runForDays(input = input.single().split(",").map { it.toInt() }, days = 256).sum()
    }

    private fun LongArray.rotateLeft() {
        val left = first()
        copyInto(this, startIndex = 1)
        set(lastIndex, left)
    }

    private fun runForDays(days: Int, input: List<Int>): LongArray {
        val store = LongArray(9).apply { input.forEach { this[it] += 1L } }
        repeat(days) {
            store.rotateLeft()
            store[6] += store[8]
        }
        return store
    }
}