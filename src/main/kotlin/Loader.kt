import java.io.File

@Suppress("unused")
class Loader {
    private val resourceDirectory = System.getProperty("user.dir") + "/src/main/resources"
    fun loadLines(day: Int, filename: String): List<String> {
        return fileOf(day, filename).readLines()
    }

    fun loadText(day: Int, filename: String): String {
        return fileOf(day, filename).readText()
    }

    private fun fileOf(day: Int, filename: String) = File(resourceDirectory, "day/$day/$filename.txt")
}