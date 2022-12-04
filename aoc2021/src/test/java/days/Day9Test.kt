package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day9Test {

    private val testData = listOf("2199943210", "3987894921", "9856789892", "8767896789", "9899965678")

    @Test
    fun part1() {
        assertEquals(expected = 15, actual = Day9.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 1134, actual = Day9.part2(testData))
    }
}