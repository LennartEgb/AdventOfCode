package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day1Test {
    private val testInput = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263")

    @Test
    fun `part1 returns fff`() {
        assertEquals(expected = 7, actual = Day1.part1(testInput))
    }
}