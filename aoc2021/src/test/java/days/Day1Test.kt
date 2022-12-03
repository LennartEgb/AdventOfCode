package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1Test {
    private val testInput = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263")

    @Test
    fun `part1 returns 7`() {
        assertEquals(expected = 7, actual = Day1.part1(testInput))
    }

    @Test
    fun `part2 returns 5`() {
        assertEquals(expected = 5, actual = Day1.part2(testInput))
    }
}