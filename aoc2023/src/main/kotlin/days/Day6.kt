package days

import Day
import days.Day6.Distance.Companion.mm
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object Day6 : Day {

    class Speed(private val distance: Distance, private val perDuration: Duration) {

        operator fun times(duration: Duration): Distance {
            val factor = duration.inWholeMilliseconds / perDuration.inWholeMilliseconds
            return distance * factor
        }

        override fun equals(other: Any?): Boolean {
            if (other !is Speed) return false
            return distance == other.distance && perDuration == other.perDuration
        }

        override fun hashCode(): Int {
            var result = distance.hashCode()
            result = 31 * result + perDuration.hashCode()
            return result
        }

        override fun toString(): String {
            return "Speed(${distance.millimeters}mm/${perDuration.inWholeMilliseconds}ms)"
        }
    }

    @JvmInline
    value class Distance private constructor(val millimeters: Long) {
        companion object {
            val Long.mm: Distance get() = Distance(this)
        }

        operator fun div(duration: Duration): Speed = Speed(distance = this, perDuration = duration)
        operator fun compareTo(distance: Distance): Int = millimeters.compareTo(distance.millimeters)
        operator fun times(factor: Long): Distance = Distance(millimeters = millimeters * factor)
    }

    data class Race(val duration: Duration, val distance: Distance)

    private fun List<String>.timesAndDistances(): List<List<Long>> = map { line ->
        line.split(" ").filter { it.isNotBlank() }.drop(1).map { it.toLong() }
    }

    private fun getRace(input: List<String>): Race {
        val (times, distances) = input.timesAndDistances()
        val time = times.joinToString("").toLong()
        val distance = distances.joinToString("").toLong()
        return Race(duration = time.milliseconds, distance = distance.mm)
    }

    private fun getRaces(input: List<String>): List<Race> {
        val (times, distances) = input.timesAndDistances()
        return times.mapIndexed { index, time -> Race(duration = time.milliseconds, distance = distances[index].mm) }
    }

    override fun part1(input: List<String>): Any = getRaces(input).map { race -> race.solve() }
        .fold(initial = 1, operation = Long::times)

    override fun part2(input: List<String>): Any = getRace(input).solve()

    private fun Race.solve(): Int {
        // NOTE: Leave out not moving and no time left cases
        val range = (0..duration.inWholeMilliseconds)
        val predicate: (Long) -> Boolean = { ((it.mm / 1L.milliseconds) * (duration - it.milliseconds)) > distance }
        val firstIndex = range.indexOfFirst(predicate)
        val lastIndexReversed = range.reversed().indexOfFirst(predicate)
        val lastIndex = range.last - lastIndexReversed + 1
        return (lastIndex - firstIndex).toInt()
    }
}
