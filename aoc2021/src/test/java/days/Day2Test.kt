package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Test {

    private val testData = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

    @Test
    fun part1() {
        assertEquals(expected = 150, actual = Day2.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 900, actual = Day2.part2(testData))
    }
}