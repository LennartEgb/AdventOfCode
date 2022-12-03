package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Test {
    private val testData = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )
    
    @Test
    fun `part1 returns 198`() {
        assertEquals(expected = 198, actual = Day3.part1(testData))
    }

    @Test
    fun `part2 returns 230`() {
        assertEquals(expected = 230, actual = Day3.part2(testData))
    }
}