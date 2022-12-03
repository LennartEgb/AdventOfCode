package days

import Day

object Day2 : Day {
    override fun part1(input: List<String>): Any {
        return input.map { Direction.of(it) }.fold(Position(0, 0)) { acc, direction -> acc + direction }
            .let { it.x * it.y }
    }

    override fun part2(input: List<String>): Any {
        return input.map { Direction.of(it) }
            .fold(Position(0, 0) to 0) { acc, direction ->
                val (position, aim) = acc
                val (newPosition, newAim) = when(direction) {
                    is Direction.Forward -> position.copy(x = position.x + direction.value, y = position.y + direction.value * aim) to aim
                    is Direction.Down -> position to (aim + direction.value)
                    is Direction.Up -> position to (aim - direction.value)
                }
                newPosition to newAim
            }.let { (position, _) -> position.x * position.y }
    }

    data class Position(val x: Int, val y: Int)

    private operator fun Position.plus(direction: Direction): Position = when (direction) {
        is Direction.Down -> copy(y = y + direction.value)
        is Direction.Forward -> copy(x = x + direction.value)
        is Direction.Up -> copy(y = y - direction.value)
    }

    sealed interface Direction {
        companion object {
            fun of(string: String): Direction {
                val (first, second) = string.split(" ")
                return when (first) {
                    "forward" -> Forward(value = second.toInt())
                    "down" -> Down(value = second.toInt())
                    "up" -> Up(value = second.toInt())
                    else -> error("Unsupported direction: $string")
                }
            }
        }

        val value: Int

        data class Forward(override val value: Int) : Direction
        data class Down(override val value: Int) : Direction
        data class Up(override val value: Int) : Direction
    }
}