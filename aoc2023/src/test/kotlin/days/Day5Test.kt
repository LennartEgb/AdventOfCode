package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    private val testData = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent().lines()

    @Test
    fun `convert source to destination`() {
        val map = Day5.RangeMap(
            name = "humidity-to-location map:",
            definitions = listOf(
                Day5.RangeDefinition(destinationStart = 60, sourceStart = 56, length = 37),
                Day5.RangeDefinition(destinationStart = 56, sourceStart = 93, length = 4),
            )
        )
        val maps = Day5.RangeMaps(listOf(map))
        // NOTE: Should convert 56..93 to 60..97 and 93..97 to 56..60
        assertEquals(expected = 60, actual = maps.convert(Day5.Seed(56)))
        assertEquals(expected = 56, actual = maps.convert(Day5.Seed(93)))
        assertEquals(expected = 96, actual = maps.convert(Day5.Seed(92)))
    }

    @Test
    fun part1() {
        assertEquals(expected = 35L, actual = Day5.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 46L, actual = Day5.part2(testData))
    }
}
