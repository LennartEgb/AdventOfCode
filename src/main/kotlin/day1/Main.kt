package day1

import Loader

fun main() {
    val loader = Loader()
    loader.loadLines(day = 1, filename = "data")
        .toCalories()
        .getElvesCalories()
        .also { println("Maximum: ${it.max()}") }
        .getTop3()
        .sum()
        .also { println("Sum of top3: $it") }
}

fun Collection<String>.toCalories(): Collection<Int?> = map { it.toIntOrNull() }
fun Collection<Int>.getTop3(): Collection<Int> = sortedDescending().take(3)
fun Collection<Int?>.getElvesCalories(): Collection<Int> {
    val elvesCount = count { it == null } + 1
    var currentElf = 0
    val elvesCalories = Array(size = elvesCount) { 0 }
    for (number in this) {
        if (number == null) currentElf += 1 else elvesCalories[currentElf] += number
    }
    return elvesCalories.toList()
}