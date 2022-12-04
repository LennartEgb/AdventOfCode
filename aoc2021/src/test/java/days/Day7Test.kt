package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day7Test {

    private val testData = listOf("16,1,2,0,4,2,7,1,2,14")
    @Test
    fun part1() {
        assertEquals(expected = 37, actual = Day7.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 168, actual = Day7.part2(testData))
    }
}