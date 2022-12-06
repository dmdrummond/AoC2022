import java.util.*

fun main() {

    fun part1(input: List<String>): Int {
        return input[0].windowed(4, 1).indexOfFirst{
            it.toSet().size == 4
        } + 4
    }

    fun part2(input: List<String>): Int {
        return input[0].windowed(14, 1).indexOfFirst{
            it.toSet().size == 14
        } + 14
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}