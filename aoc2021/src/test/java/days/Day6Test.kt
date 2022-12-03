package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day6Test {

    private val testData = listOf("3,4,3,1,2")

    @Test
    fun part1() {
        assertEquals(expected = 5934L, actual = Day6.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 26984457539L, actual = Day6.part2(testData))
    }
}