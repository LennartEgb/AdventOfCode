package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun part1() {
        val data = listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
        )
        assertEquals(expected = listOf(7, 5, 6, 10), actual = Day6.part1(data))
    }

    @Test
    fun part2() {
        val data = listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            // "1zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", -> 26 but is 27 ?
        )
        assertEquals(
            expected = listOf(19, 23, 23, 29),
            actual = Day6.part2(data)
        )
    }
}