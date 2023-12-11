package days

import Day

private typealias SchematicRow = List<Day3.Element>

// It's not: 542418
object Day3 : Day {

    sealed interface Element
    data class Symbol(val value: Char, val column: Int, val row: Int) : Element
    data class Number(val value: Int, val xRange: IntRange, val row: Int) : Element {
        val expandedColumn: IntRange = xRange.first - 1..xRange.last + 1
        val expandedRow: IntRange = row - 1..row + 1
    }

    private val numberRegex = """\d+""".toRegex()
    private val symbolRegex = """[^.;^\d^\n]+""".toRegex()

    override fun part1(input: List<String>): Any {
        val elements = input.mapIndexed(::extractElements).flatten()

        println("----------- manual check -----------")
        println("Found numbers: ${elements.filterIsInstance<Number>().size}")
        println("Found symbols: ${elements.filterIsInstance<Symbol>().size}")

        println("----------- Regex check -----------")
        println("Found numbers: ${numberRegex.findAll(input.joinToString("\n")).count()}")
        println("Found numbers: ${symbolRegex.findAll(input.joinToString("\n")).count()}")

        return elements.findParts().sumOf { it.value }
    }

    override fun part2(input: List<String>): Any {
        return "Implement"
    }

    private fun extractElements(row: Int, line: String): List<Element> = buildList {
        var numberStart = -1
        var numberValue = ""
        for ((column, c) in line.withIndex()) {
            when {
                c.isDigit() -> {
                    numberValue += c
                    if (numberStart == -1) numberStart = column
                }

                else -> {
                    if (c != '.') add(Symbol(value = c, column = column, row = row))
                    if (numberValue.isNotBlank()) {
                        add(Number(value = numberValue.toInt(), xRange = numberStart..column, row = row))
                        numberStart = -1
                        numberValue = ""
                    }
                }
            }
        }
        if (numberValue.isNotBlank()) {
            add(Number(value = numberValue.toInt(), xRange = numberStart..line.length, row = row))
        }
    }

    private fun SchematicRow.findParts(): List<Number> {
        val numbers = filterIsInstance<Number>()
        val symbols = filterIsInstance<Symbol>()
        return numbers.filter { number -> symbols.any { symbol -> symbol in number } }
    }

    private operator fun Number.contains(symbol: Symbol): Boolean =
        symbol.row in expandedRow && symbol.column in expandedColumn
}

