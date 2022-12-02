package day2

import Loader

fun main() {
    val loader = Loader()
    val lines = loader.loadLines(2, "data")
    lines.sumOf { parsePart1(it).score }.also { println(it) }
    lines.sumOf { parsePart2(it).score }.also { println(it) }
}

fun parsePart1(value: String): Game {
    val (opponent, _, own) = value.toCharArray()
    return Game(opponent = getPlay(opponent), own = getPlay(own))
}

fun parsePart2(value: String): Game {
    val (opponent, _, outcome) = value.toCharArray()
    val play = getPlay(opponent)
    val result = getOutcome(outcome)
    val ownPlay = getPlay(play, result)
    return Game(opponent = play, own = ownPlay)
}

fun getPlay(char: Char) = when (char) {
    'A', 'X' -> Play.Rock
    'B', 'Y' -> Play.Paper
    'C', 'Z' -> Play.Scissors
    else -> error("Unsupported char: $char")
}

fun getOutcome(char: Char) = when (char) {
    'X' -> Outcome.Loss
    'Y' -> Outcome.Draw
    'Z' -> Outcome.Win
    else -> error("Unsupported outcome $char")
}

fun getScore(opponent: Play, own: Play): Int = getOutcome(opponent, own).score + own.score

fun getPlay(opponent: Play, outcome: Outcome): Play = when (opponent) {
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

fun getOutcome(opponent: Play, own: Play): Outcome = when (opponent) {
    Play.Rock -> when (own) {
        Play.Rock -> Outcome.Draw
        Play.Paper -> Outcome.Win
        Play.Scissors -> Outcome.Loss
    }

    Play.Paper -> when (own) {
        Play.Rock -> Outcome.Loss
        Play.Paper -> Outcome.Draw
        Play.Scissors -> Outcome.Win
    }

    Play.Scissors -> when (own) {
        Play.Rock -> Outcome.Win
        Play.Paper -> Outcome.Loss
        Play.Scissors -> Outcome.Draw
    }
}

data class Game(val opponent: Play, val own: Play) {
    val score: Int = getOutcome(opponent, own).score + own.score
}

enum class Outcome(val score: Int) {
    Win(score = 6),
    Draw(score = 3),
    Loss(score = 0),
}

enum class Play(val score: Int) {
    Rock(score = 1),
    Paper(score = 2),
    Scissors(score = 3);
}