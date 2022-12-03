fun main() {
    fun part1(input: List<String>): Int {
        fun calculateScore(input: String): Int {
            val choiceScore = when(input[2]) {
                'X' -> 1
                'Y' -> 2
                'Z' -> 3
                else -> throw Error("Unable to process $input")
            }
            val winScore = when (input) {
                "A X", "B Y", "C Z" -> 3
                "A Y", "B Z", "C X" -> 6
                "A Z", "B X", "C Y" -> 0
                else -> throw Error ("Not sure what $input means")
            }
            return choiceScore + winScore
        }
        return input.sumOf { calculateScore(it) }
    }

    fun part2(input: List<String>): Int {
        fun calculateScore(input: String): Int {
            val winScore = when (input[2]) {
                'X' -> 0
                'Y' -> 3
                'Z' -> 6
                else -> throw Error("Not sure what $input means")
            }

            val choiceScore = when (input) {
                "A X", "C Y", "B Z" -> 3
                "C X", "B Y", "A Z" -> 2
                "B X", "A Y", "C Z" -> 1
                else -> throw Error("Unable to process $input")
            }

            return winScore + choiceScore
        }

        return input.sumOf { calculateScore(it) }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
