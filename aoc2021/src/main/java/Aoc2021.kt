import days.Day1
import days.Day2
import days.Day3
import days.Day4

class Aoc2021 : AoC() {
    override val days: List<Day> get() = listOf(Day1, Day2, Day3, Day4)
    override val loader: Loader get() = Loader(year = "aoc2021")
}