package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Test {
    val testInput = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent().lines()

    @Test
    fun `solution part 1`() {
        assertEquals(expected = 8, Day2.part1(testInput))
    }

    @Test
    fun `solution part 2`() {
        assertEquals(expected = 2286, actual = Day2.part2(testInput))
    }

}
