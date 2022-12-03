package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Test {
    private val testData = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw"
    )

    @Test
    fun part1() {
        assertEquals(expected = 157, actual = Day3.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 70, actual = Day3.part2(testData))
    }
}