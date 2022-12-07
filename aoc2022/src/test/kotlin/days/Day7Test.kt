package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class Day7Test {

    private val testData = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k"
    )

    @Test
    fun part1() {
        assertEquals(expected = 95437L, actual = Day7.part1(testData))
    }

    @Test
    fun part2() {
        assertEquals(expected = 24933642L, Day7.part2(testData))
    }
}