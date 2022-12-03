package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day5Test {

    private val testData = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    @Test
    fun part1() {
        assertEquals(expected = 5, actual = Day5.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 12, actual = Day5.part2(testData))
    }
}