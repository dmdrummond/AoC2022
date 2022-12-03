fun main() {

    fun priorityForCharacter(char: Char): Int {
        val code = char.code
        return if (code > 96 ) {
            code - 96
        } else {
            code - 38
        }
    }

    fun splitAndFindCommonCharacter(input: String): Char {
        val first = input.substring(0 until input.length / 2)
        val second = input.substring(input.length/2 until input.length)
        for (char in first) {
            if (second.contains(char)){
                return char
            }
        }
        throw Error("No duplicate found")
    }
    fun findCommonElement(input1: String, input2: String, input3: String): Char {
        for (char in input1) {
            if (input2.contains(char) && input3.contains(char)){
                return char
            }
        }
        throw Error("No badge found")
    }

    fun part1(input: List<String>) = input
        .map { splitAndFindCommonCharacter(it) }
        .sumOf { priorityForCharacter(it) }

    fun part2(input: List<String>) = input
        .chunked(3)
        .map { findCommonElement(it[0], it[1], it[2]) }
        .sumOf { priorityForCharacter(it) }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
