import java.io.File

class Loader(year: String) {
    private val resourceDirectory = System.getProperty("user.dir") + "/$year/src/main/resources"
    fun loadLines(day: Int, filename: String): List<String> {
        return fileOf(day, filename).readLines()
    }

    private fun fileOf(day: Int, filename: String) = File(resourceDirectory, "day/$day/$filename.txt")
}