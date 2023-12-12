package days

import Day

object Day5 : Day {

    @JvmInline
    value class RangeMaps(private val list: List<RangeMap>) {
        fun convert(seed: Seed): Long {
            return list.fold(initial = seed.value) { acc, rangeMap ->
                rangeMap.convert(value = acc)
            }
        }
    }

    data class RangeMap(val name: String, val definitions: List<RangeDefinition>) {
        fun convert(value: Long): Long {
            return definitions.firstOrNull { value in it.sourceRange }?.convert(value) ?: value
        }
    }

    data class RangeDefinition(
        val destinationStart: Long,
        val sourceStart: Long,
        val length: Long,
    ) {
        val destinationRange: LongRange = destinationStart..destinationStart + length
        val sourceRange: LongRange = sourceStart..<sourceStart + length
        fun convert(value: Long): Long {
            require(value in sourceRange)
            val delta = value - sourceStart
            return destinationStart + delta
        }
    }

    @JvmInline
    value class Seed(val value: Long)

    private fun String.getLongList(): List<Long> = substringAfter(":")
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }

    private fun String.getSeeds(): List<Seed> = getLongList().map { Seed(it) }

    private fun String.getDefinition(): RangeDefinition = split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }
        .let { (dest, src, length) ->
            RangeDefinition(destinationStart = dest, sourceStart = src, length = length)
        }

    private val digitRegex = """\d+""".toRegex()

    private fun List<String>.getRangeMaps(): RangeMaps {
        val rangeMaps = mutableListOf<RangeMap>()
        var name: String? = null
        var definitions: MutableList<RangeDefinition>? = null

        fun createAndAdd() {
            name?.also {
                // for safety in case of first line
                val map = RangeMap(name = it, definitions = requireNotNull(definitions))
                rangeMaps.add(map)
            }
        }

        filter { it.isNotBlank() }.forEachIndexed { index, line ->
            if (line.contains(digitRegex)) {
                if (definitions == null) definitions = mutableListOf()
                line.getDefinition().also { definitions?.add(it) }
            } else {
                createAndAdd()
                // line contains no numbers and is the title of the next map
                name = line
                definitions = null
            }
        }
        createAndAdd()

        return RangeMaps(list = rangeMaps)
    }

    override fun part1(input: List<String>): Any {
        val seeds = input.first().getSeeds()
        val rangeMaps = input.subList(fromIndex = 1, toIndex = input.size).getRangeMaps()
        val locations = seeds.map { rangeMaps.convert(it) }
        return locations.min()
    }

    override fun part2(input: List<String>): Any {
        val seeds = input.first().getLongList()
            .windowed(2, 2)
            .flatMap { (start, range) -> (start..<start + range).map { Seed(it) } }
        val rangeMaps = input.subList(fromIndex = 1, toIndex = input.size).getRangeMaps()
        val locations = seeds.map { rangeMaps.convert(it) }
        return locations.min()
    }
}
