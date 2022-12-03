import days.*

class Aoc2021 : AoC() {
    override val days: List<Day> get() = listOf(Day1, Day2, Day3, Day4, Day5)
    override val loader: Loader get() = Loader(year = "aoc2021")
}