package days

import Day

object Day4 : Day {
    override fun part1(input: List<String>): Any {
        val boards = input.drop(2).joinToString(separator = "\n").split("\n\n").map { Board(it.split("\n")) }

        val game = input.first()
        val fields = game.split(",").map { it.toInt() }
        for (field in fields) {
            for (board in boards) {
                board.sign(field)
                if (board.hasWon()) {
                    return board.getScore() * field
                }
            }
        }
        error("No board has won")
    }

    override fun part2(input: List<String>): Any {
        var boards = input.drop(2).joinToString(separator = "\n").split("\n\n").map { Board(it.split("\n")) }
        val winningBoards = mutableListOf<Pair<Board, Int>>()

        val game = input.first()
        val fields = game.split(",").map { it.toInt() }
        for (field in fields) {
            for (board in boards) {
                board.sign(field)
                if (board.hasWon()) {
                    winningBoards.add(board to field)
                    boards = boards - board
                }
            }
        }

        return winningBoards.last().let { it.first.getScore() * it.second }
    }

    data class Field(val value: Int, val hit: Boolean)
    class Board private constructor(private val array: Array<Array<Field>>) {
        constructor(instruction: List<String>) : this(
            Array(instruction.size) { row ->
                val fields = instruction[row].replace("  ", " ").removePrefix(" ").split(" ")
                Array(fields.size) { column ->
                    Field(fields[column].toInt(), hit = false)
                }
            }
        )

        fun sign(value: Int) {
            array.forEachIndexed { row, rows ->
                rows.forEachIndexed { column, field ->
                    array[row][column] = if (field.value == value) field.copy(hit = true) else field
                }
            }
        }

        fun hasWon(): Boolean {
            array.forEach { fields -> if (fields.all { it.hit }) return true }
            array.indices.forEach { column -> if (array.map { it[column] }.all { it.hit }) return true }
            return false
        }

        fun getScore(): Int = array.sumOf { fields ->
            fields.sumOf { field -> if (field.hit) 0 else field.value }
        }
    }
}