package days

import org.junit.jupiter.api.Test
import kotlin.math.exp
import kotlin.test.assertEquals

internal class Day1Test {

    @Test
    fun testPart1() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()
        assertEquals(expected = 142, actual = Day1.part1(input))
    }

    @Test
    fun testPart2() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines()
        assertEquals(expected = 281, actual = Day1.part2(input))
    }
}
