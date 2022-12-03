package days

import Day

object Day1: Day {
    override fun part1(input: List<String>): Any {
        return input.toCalories().getElvesCalories().max()
    }

    override fun part2(input: List<String>): Any {
        return input.toCalories().getElvesCalories().getTop3().sum()
    }

    private fun Collection<String>.toCalories(): Collection<Int?> = map { it.toIntOrNull() }
    private fun Collection<Int>.getTop3(): Collection<Int> = sortedDescending().take(3)
    private fun Collection<Int?>.getElvesCalories(): Collection<Int> {
        val elvesCount = count { it == null } + 1
        var currentElf = 0
        val elvesCalories = Array(size = elvesCount) { 0 }
        for (number in this) {
            if (number == null) currentElf += 1 else elvesCalories[currentElf] += number
        }
        return elvesCalories.toList()
    }
}

