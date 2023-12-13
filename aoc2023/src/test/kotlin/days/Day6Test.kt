package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    private val testData = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent().lines()
    @Test
    fun part1() {
        assertEquals(expected = 288, actual = Day6.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 71503, actual = Day6.part2(testData))
    }
}
