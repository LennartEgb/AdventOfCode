package days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day4Test {

    private val testData = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7",
    )

    @Test
    fun part1() {
        assertEquals(expected = 4512, actual = Day4.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 1924, actual = Day4.part2(testData))
    }

    @Nested
    inner class Board {
        private val boardInstruction = listOf(
            "94 97 41 22 48",
            "21 47 72 23 26",
            "12 81 86 24 91",
            "71 78 90 59 54",
            "92 63 68 65  1"
        )

        @Test
        fun `horizontal bingo has won`() {
            val board = Day4.Board(boardInstruction)
            board.sign(value = 94)
            board.sign(value = 97)
            board.sign(value = 41)
            board.sign(value = 22)
            board.sign(value = 48)

            assertTrue(actual = board.hasWon())
        }

        @Test
        fun `vertical bingo has won`() {
            val board = Day4.Board(boardInstruction)
            board.sign(value = 94)
            board.sign(value = 21)
            board.sign(value = 12)
            board.sign(value = 71)
            board.sign(value = 92)

            assertTrue(actual = board.hasWon())
        }

        @Test
        fun `no hits has not won`() {
            val board = Day4.Board(boardInstruction)
            assertFalse(actual = board.hasWon())
        }

    }
}