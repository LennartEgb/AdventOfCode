import days.Day1
import days.Day2
import days.Day3

fun main() {
    val days: List<Day> = listOf(
        Day1,
        Day2,
        Day3,
    )
    val loader = Loader()
    days.forEachIndexed { index, day ->
        val input = loader.loadLines(day =index + 1, filename = "data")
        buildString {
            appendLine("DAY ${index + 1} ----------------")
            appendLine("Part 1: ${day.part1(input = input)}")
            appendLine("Part 2: ${day.part2(input = input)}")
        }.also(::print)
    }
}