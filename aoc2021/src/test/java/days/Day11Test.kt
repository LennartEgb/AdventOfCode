package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day11Test {

    private val testData = listOf(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    )

    @Test
    fun part1() {
        assertEquals(expected = 1656, actual = Day11.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 195, actual = Day11.part2(testData))
    }
}