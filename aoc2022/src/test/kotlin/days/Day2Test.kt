package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Test {
    @Test
    fun testPart1() {
        assertEquals(expected = 15, actual = Day2.part1(listOf("A Y", "B X", "C Z")))
    }

    @Test
    fun testPart2() {
        assertEquals(expected = 12, actual = Day2.part2(listOf("A Y", "B X", "C Z")))
    }
}