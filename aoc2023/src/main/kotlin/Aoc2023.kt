import days.*

class Aoc2023 : AoC() {
    override val days: List<Day> get() = listOf(Day1, Day2, Day3, Day4, Day5, Day6)
    override val loader: Loader get() = Loader(year = "aoc2023")
}
