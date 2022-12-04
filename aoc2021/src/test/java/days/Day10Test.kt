package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day10Test {

    private val testData = listOf<String>(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    )

    @Test
    fun part1() {
        assertEquals(expected = 26397L, actual = Day10.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 288957L, actual = Day10.part2(testData))
    }
}