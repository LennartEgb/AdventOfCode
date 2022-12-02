package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2KtTest {
    @Test
    fun `opponent rock and own paper returns 8`() {
        assertEquals(expected = 8, actual = getScore(Play.Rock, Play.Paper))
    }

    @Test
    fun `opponent paper and own rock returns 1`() {
        assertEquals(expected = 1, actual = getScore(Play.Paper, Play.Rock))
    }

    @Test
    fun `opponent scissors and own scissors returns 6`() {
        assertEquals(expected = 6, actual = getScore(Play.Scissors, Play.Scissors))
    }
}