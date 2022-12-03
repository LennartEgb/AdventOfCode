import days.Day1
import days.Day2

class Aoc2021 : AoC() {
    override val days: List<Day> get() = listOf(Day1, Day2)
    override val loader: Loader get() = Loader(year = "aoc2021")
}