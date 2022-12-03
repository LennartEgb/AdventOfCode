abstract class Main {
    abstract val days: List<Day>
    abstract val loader: Loader
    fun run() {
        days.forEachIndexed { index, day ->
            val input = loader.loadLines(day = index + 1, filename = "data")
            buildString {
                appendLine("DAY ${index + 1} ----------------")
                appendLine("Part 1: ${day.part1(input = input)}")
                appendLine("Part 2: ${day.part2(input = input)}")
            }.also(::print)
        }
    }
}
