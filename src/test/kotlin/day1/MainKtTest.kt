package day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class MainKtTest {

    private val testdata = listOf(
        1000,
        2000,
        3000,
        null,
        4000,
        null,
        5000,
        6000,
        null,
        7000,
        8000,
        9000,
        null,
        10000,
    )

    @Test
    fun getElvesCalories() {
        assertEquals(expected = listOf(6000, 4000, 11000, 24000, 10000), actual = testdata.getElvesCalories())
    }
}