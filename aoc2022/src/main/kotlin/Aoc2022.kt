import days.Day1
import days.Day2
import days.Day3

class Aoc2022 : AoC() {
    override val days: List<Day> get() = listOf(Day1, Day2, Day3)
    override val loader: Loader get() = Loader(year = "aoc2022")
}