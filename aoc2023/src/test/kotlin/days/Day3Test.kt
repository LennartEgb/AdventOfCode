package days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day3Test {

    @Nested
    inner class Part1 {
        @Test
        fun `aoc official test`() {
            val testInput = """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
            """.trimIndent().lines()
            assertEquals(expected = 4361, Day3.part1(testInput))
        }

        @Test
        fun `small test`() {
            val testInput2 = """
                ........
                .24..4..
                ......*.
            """.trimIndent().lines()
            assertEquals(expected = 4, Day3.part1(testInput2))
        }

        @Test
        fun `end number`() {
            val testInput2 = """
                ........
                .24....4
                ......*.
            """.trimIndent().lines()
            assertEquals(expected = 4, Day3.part1(testInput2))
        }

        @Test
        fun `end line and row number`() {
            val testInput2 = """
                ........
                ......*.
                .24....4
            """.trimIndent().lines()
            assertEquals(expected = 4, Day3.part1(testInput2))
        }

        @Test
        fun `start line and beginning row number`() {
            val testInput2 = """
                1*......
                ........
                .24....4
            """.trimIndent().lines()
            assertEquals(expected = 1, Day3.part1(testInput2))
        }
    }


    @Test
    fun `solution part 2`() {
        assertEquals(expected = -1, actual = Day3.part2(emptyList()))
    }

}
