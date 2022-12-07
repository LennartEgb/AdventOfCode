package days

import Day
import kotlin.math.abs

object Day7 : Day {

    data class File(val name: String, val size: Long)
    class Directory(val name: String, val parent: Directory? = null) {
        private val files = mutableListOf<File>()
        private val directories = mutableListOf<Directory>()
        val size: Long get() = files.sumOf { it.size } + directories.sumOf { it.size }
        fun getDirectory(name: String): Directory = directories.first { it.name == name }
        fun add(file: File) = files.add(file)
        fun add(directory: Directory) = directories.add(directory)
    }

    private fun parse(input: List<String>): List<Directory> {
        val root = Directory(name = "/", parent = null)
        var current = root
        val directories = mutableListOf(root)

        fun addDir(name: String) = Directory(name = name, parent = current).also(current::add).also(directories::add)
        fun String.toFile(): File = File(name = substringAfter(" "), size = substringBefore(" ").toLong())

        input.forEach { line ->
            when {
                line.startsWith("$ ls") -> Unit
                line.startsWith("$ cd ..") -> current = requireNotNull(current.parent)
                line.startsWith("$ cd /") -> current = root
                line.startsWith("$ cd") -> current = current.getDirectory(line.split(" ").last())
                line.startsWith("dir") -> addDir(name = line.split(" ").last())
                else -> current.add(line.toFile())
            }
        }
        return directories
    }

    override fun part1(input: List<String>): Any {
        val directories = parse(input)
        return directories.map { it.size }.filter { it < 100_000 }.sum()
    }

    override fun part2(input: List<String>): Any {
        val directories = parse(input)
        val root = directories.first()
        val neededSpace = 30_000_000
        val availableSpace = 70_000_000 - root.size
        val diff = abs(availableSpace - neededSpace)
        return directories.filter { it.size > diff }.minBy { it.size }.size
    }
}