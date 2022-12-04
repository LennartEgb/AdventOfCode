package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day4Test {

    private val testData = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8"
    )

    @Test
    fun part1() {
        assertEquals(expected = 2, actual = Day4.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 4, actual = Day4.part2(testData))
    }
}