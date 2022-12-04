package days

import Day

object Day10 : Day {

    private val scoreTable = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    override fun part1(input: List<String>): Any {
        return input.mapNotNull { line -> line.getCorruptedSymbol() }.sumOf { requireNotNull(scoreTable[it]) }
    }

    private val scoreTable2 = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4,
    )

    override fun part2(input: List<String>): Any {
        return input.filter { line -> line.getCorruptedSymbol() == null }
            .map { it.getHeap().fold(0L) { acc, c -> acc * 5 + requireNotNull(scoreTable2[c]) { } } }
            .sorted()
            .let { it[it.size / 2] }
    }


    private fun Char.isCloser(): Boolean = this in scoreTable.keys
    private fun Char.inverse(): Char = when (this) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> error("Unsupported char: $this")
    }

    private fun String.getCorruptedSymbol(): Char? {
        val heap = SymbolHeap()
        return firstOrNull { symbol ->
            if (symbol.isCloser()) {
                if (symbol == heap.current?.inverse()) {
                    heap.pop()
                } else {
                    return@firstOrNull true
                }
            } else {
                heap.add(symbol)
            }
            false
        }
    }

    private fun String.getHeap(): String {
        val heap = SymbolHeap()
        forEach { symbol ->
            if (symbol.isCloser()) {
                if (symbol == heap.current?.inverse()) {
                    heap.pop()
                }
            } else {
                heap.add(symbol)
            }
        }
        return heap.rest
    }

    private class SymbolHeap {
        private val symbols = mutableListOf<Char>()
        val current: Char? get() = symbols.getOrNull(0)
        fun add(c: Char) = symbols.add(0, c)
        fun pop() {
            symbols.removeAt(0)
        }

        val rest: String get() = symbols.map { it.inverse() }.joinToString("")
    }
}
