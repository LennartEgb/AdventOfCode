package days

import Day

object Day2 : Day {
    override fun part1(input: List<String>): Any {
        fun parse(value: String): Game {
            val (opponent, _, own) = value
            return Game(opponent = getPlay(opponent), own = getPlay(own))
        }
        return input.sumOf { parse(it).score }
    }

    override fun part2(input: List<String>): Any {
        fun parse(value: String): Game {
            val (opponent, _, outcome) = value
            val play = getPlay(opponent)
            val result = getOutcome(outcome)
            val ownPlay = getPlay(play, result)
            return Game(opponent = play, own = ownPlay)
        }
        return input.sumOf { parse(it).score }
    }

    private fun getPlay(char: Char) = when (char) {
        'A', 'X' -> Play.Rock
        'B', 'Y' -> Play.Paper
        'C', 'Z' -> Play.Scissors
        else -> error("Unsupported char: $char")
    }

    private fun getOutcome(char: Char) = when (char) {
        'X' -> Outcome.Loss
        'Y' -> Outcome.Draw
        'Z' -> Outcome.Win
        else -> error("Unsupported outcome $char")
    }

    private fun getPlay(opponent: Play, outcome: Outcome): Play = when (opponent) {
        Play.Rock -> when (outcome) {
            Outcome.Win -> Play.Paper
            Outcome.Draw -> Play.Rock
            Outcome.Loss -> Play.Scissors
        }

        Play.Paper -> when (outcome) {
            Outcome.Win -> Play.Scissors
            Outcome.Draw -> Play.Paper
            Outcome.Loss -> Play.Rock
        }

        Play.Scissors -> when (outcome) {
            Outcome.Win -> Play.Rock
            Outcome.Draw -> Play.Scissors
            Outcome.Loss -> Play.Paper
        }
    }

    private data class Game(val opponent: Play, val own: Play) {
        val score: Int = opponent.play(own).score + own.score
    }

    private operator fun String.component1(): Char = get(0)
    private operator fun String.component2(): Char = get(1)
    private operator fun String.component3(): Char = get(2)

    private enum class Outcome { Loss, Draw, Win }
    private enum class Play { Rock, Paper, Scissors }

    private val Outcome.score: Int get() = ordinal * 3
    private val Play.score: Int get() = ordinal + 1

    private fun Play.play(other: Play): Outcome = when (this) {
        Play.Rock -> when (other) {
            Play.Rock -> Outcome.Draw
            Play.Paper -> Outcome.Win
            Play.Scissors -> Outcome.Loss
        }

        Play.Paper -> when (other) {
            Play.Rock -> Outcome.Loss
            Play.Paper -> Outcome.Draw
            Play.Scissors -> Outcome.Win
        }

        Play.Scissors -> when (other) {
            Play.Rock -> Outcome.Win
            Play.Paper -> Outcome.Loss
            Play.Scissors -> Outcome.Draw
        }
    }
}
