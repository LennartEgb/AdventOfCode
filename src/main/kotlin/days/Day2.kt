package days

import Day

object Day2 : Day {
    override fun part1(input: List<String>): Any {
        fun parse(value: String): Game {
            val (opponent, _, own) = value.toCharArray()
            return Game(opponent = getPlay(opponent), own = getPlay(own))
        }
        return input.sumOf { parse(it).score }
    }

    override fun part2(input: List<String>): Any {
        fun parse(value: String): Game {
            val (opponent, _, outcome) = value.toCharArray()
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

    private enum class Outcome(val score: Int) {
        Win(score = 6),
        Draw(score = 3),
        Loss(score = 0),
    }

    private enum class Play(val score: Int) {
        Rock(score = 1),
        Paper(score = 2),
        Scissors(score = 3);

        infix fun play(other: Play): Outcome = when (this) {
            Rock -> when (other) {
                Rock -> Outcome.Draw
                Paper -> Outcome.Win
                Scissors -> Outcome.Loss
            }

            Paper -> when (other) {
                Rock -> Outcome.Loss
                Paper -> Outcome.Draw
                Scissors -> Outcome.Win
            }

            Scissors -> when (other) {
                Rock -> Outcome.Win
                Paper -> Outcome.Loss
                Scissors -> Outcome.Draw
            }
        }
    }
}
