package days

import days.Day1
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1Test {
    private val testInput = listOf("1000", "2000", "3000", "", "4000", "", "5000", "6000", "", "7000", "8000", "9000", "", "10000")

    @Test
    fun testPart1() {
        assertEquals(expected = 24000, actual = Day1.part1(testInput))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 45000, actual = Day1.part2(testInput))
    }
}