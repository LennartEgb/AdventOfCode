package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {
    private val testData = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )

    @Test
    fun part1() {
        assertEquals(expected = 21, actual = Day8.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 8, actual = Day8.part2(testData))
    }
}