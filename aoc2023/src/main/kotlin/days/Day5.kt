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

    data class RangeMap(val name: String, val definitions: List<Block>) {
        fun convert(value: Long): Long {
            return definitions.firstOrNull { value in it.sourceRange }?.convert(value) ?: value
        }
    }

    data class Block(val dst: Long, val src: Long, val length: Long) {
        val sourceRange: LongRange = src..<src + length
        fun convert(value: Long): Long {
            require(value in sourceRange)
            val delta = value - src
            return dst + delta
        }
    }

    @JvmInline
    value class Seed(val value: Long)

    private fun String.getLongList(): List<Long> = substringAfter(":")
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }

    private fun String.getSeeds(): List<Seed> = getLongList().map { Seed(it) }

    private fun String.getDefinition(): Block = split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }
        .let { (dest, src, length) ->
            Block(dst = dest, src = src, length = length)
        }

    private val digitRegex = """\d+""".toRegex()

    private fun List<String>.getRangeMaps(): RangeMaps {
        val rangeMaps = mutableListOf<RangeMap>()
        var name: String? = null
        var definitions: MutableList<Block>? = null

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
        val rangeMaps = input.drop(2).getRangeMaps()
        val locations = seeds.map { rangeMaps.convert(it) }
        return locations.min()
    }

    override fun part2(input: List<String>): Any {
        // NOTE: This solution is too slow. Use ephemient to go on and live my life
        // val seeds = input.first().getLongList()
        //     .windowed(2, 2)
        //     .flatMap { (start, range) -> (start..<start + range).map { Seed(it) } }
        // val rangeMaps = input.drop(2).getRangeMaps()
        // val locations = seeds.map { rangeMaps.convert(it) }
        // return locations.min()
        return Day5ByEphemient(input.joinToString("\n"))
            .part2()
    }
}


class Day5ByEphemient(input: String) {
    private val seeds: List<Long>
    private val mappingsList: List<List<Mapping>>

    init {
        val stanzas = input.split("\n\n")
        seeds = stanzas[0].split(' ').mapNotNull { it.toLongOrNull() }
        mappingsList = stanzas.drop(1).map { stanza ->
            stanza.lines().mapNotNull { line ->
                val (dest, source, size) = line.split(' ').takeIf { it.size == 3 } ?: return@mapNotNull null
                Mapping(
                    dest.toLongOrNull() ?: return@mapNotNull null,
                    source.toLongOrNull() ?: return@mapNotNull null,
                    size.toLongOrNull() ?: return@mapNotNull null,
                )
            }.sortedBy { it.source }
        }
    }


    fun part2(): Long {
        return seeds.chunked(2) { (start, length) -> start until start + length }.flatMap {
            mappingsList.fold(listOf(it)) { acc, mappings ->
                buildList {
                    for (range in acc) {
                        val last = mappings.filter { mapping -> range in mapping }.fold(range.first) { first, mapping ->
                            if (first < mapping.source) add(first until mapping.source)
                            val start = maxOf(first, mapping.source)
                            val end = minOf(range.last + 1, mapping.source + mapping.length)
                            val offset = mapping.dest - mapping.source
                            add(start + offset until end + offset)
                            end
                        }
                        if (last <= range.last) add(last..range.last)
                    }
                }
            }
        }.minOf { it.first }
    }

    private data class Mapping(val dest: Long, val source: Long, val length: Long) {
        operator fun contains(range: LongRange): Boolean =
            !range.isEmpty() && source <= range.last && range.first < source + length
    }
}

