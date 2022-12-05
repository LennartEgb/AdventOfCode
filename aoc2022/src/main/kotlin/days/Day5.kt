package days

import Day

typealias Crates = MutableMap<Int, List<Char>>

object Day5 : Day {

    private data class Command(val amount: Int, val from: Int, val to: Int)

    private operator fun <E> List<E>.component6(): E = get(5)
    private fun String.toCommand(): Command = split(" ").let { (_, amount, _, from, _, to) ->
        Command(amount = amount.toInt(), from = from.toInt(), to = to.toInt())
    }

    private fun String.toCrates(): Crates {
        val mirroredCrates = split("\n")
            .map { row -> row.windowed(size = 3, step = 4).map { it[1] } }
        return (1..mirroredCrates.maxOf { it.size }).associateWith { index ->
            mirroredCrates.mapNotNull { it.getOrNull(index - 1)?.takeIf { c -> c != ' ' && !c.isDigit() } }
        }.toMutableMap()
    }

    private fun parse(input: List<String>): Pair<Crates, List<Command>> = input
        .joinToString("\n")
        .split("\n\n")
        .let { (first, last) -> first.toCrates() to last.split("\n").map { s -> s.toCommand() } }

    private fun Crates.getTopLetters(): String = values.mapNotNull { it.firstOrNull() }.joinToString("")
    private infix fun Crates.run(command: Command) = move(command.amount, command.from, command.to)
    private fun Crates.move(n: Int, from: Int, to: Int) {
        val movable = getValue(from).take(n)
        replaceAll { index, list ->
            when (index) {
                from -> list.drop(n)
                to -> movable + list
                else -> list
            }
        }
    }

    override fun part1(input: List<String>): Any {
        val (crates, commands) = parse(input)
        commands.forEach { command ->
            repeat(command.amount) {
                crates.move(n = 1, from = command.from, to = command.to)
            }
        }
        return crates.getTopLetters()
    }

    override fun part2(input: List<String>): Any {
        val (crates, commands) = parse(input)
        commands.forEach { command -> crates run command }
        return crates.getTopLetters()
    }
}

