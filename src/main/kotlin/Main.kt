import days.Day1
import days.Day2

fun main() {
    val days: List<Day> = listOf(
        Day1,
        Day2,
    )
    val loader = Loader()
    days.forEachIndexed { index, day ->
        val input = loader.loadLines(day = index + 1, filename = "data")
        day.part1(input = input)
        day.part2(input = input)
    }
}