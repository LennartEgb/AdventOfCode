package days

import Day

object Day2 : Day {

    data class Bag(
        val red: Int,
        val green: Int,
        val blue: Int,
    )

    data class Game(val id: Int, val bags: List<Bag>) {
        companion object {
            private val gameRegex = """Game (\d+)""".toRegex()
            private val redRegex = """(\d+) red""".toRegex()
            private val greenRegex = """(\d+) green""".toRegex()
            private val blueRegex = """(\d+) blue""".toRegex()

            operator fun invoke(line: String): Game {
                val (game, bagsLine) = line.split(": ")
                val id = requireNotNull(gameRegex.find(game)?.groupValues?.get(1)?.toInt())
                fun Regex.intOfBag(line: String): Int = find(line)?.groupValues?.get(1)?.toInt() ?: 0
                val bags = bagsLine.split("; ").map { bag ->
                    Bag(
                        red = redRegex.intOfBag(bag),
                        green = greenRegex.intOfBag(bag),
                        blue = blueRegex.intOfBag(bag),
                    )
                }
                return Game(id, bags)
            }
        }

        fun isPossibleWith(bag: Bag): Boolean {
            return bags.none { it.red > bag.red || it.green > bag.green || it.blue > bag.blue }
        }

        fun power(): Int {
            val red = bags.maxOf { it.red }
            val green = bags.maxOf { it.green }
            val blue = bags.maxOf { it.blue }
            return red * green * blue
        }
    }

    private val possibleBag = Bag(red = 12, green = 13, blue = 14)

    override fun part1(input: List<String>): Any =
        input.sumOf { line -> Game(line).takeIf { it.isPossibleWith(possibleBag) }?.id ?: 0 }

    override fun part2(input: List<String>): Any = input.sumOf { line -> Game(line).power() }
}

