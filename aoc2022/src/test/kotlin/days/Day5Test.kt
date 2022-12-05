package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    private val testData = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2"
    )

    @Test
    fun part1() {
        assertEquals(expected = "CMZ", actual = Day5.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = "MCD", actual = Day5.part2(testData))
    }
}