fun main() {

    fun toRanges(input: String): Pair<IntRange, IntRange> {
        val (range1Str, range2Str) = input.split(',')
        val (range1Start, range1End) = range1Str.split('-')
        val (range2Start, range2End) = range2Str.split('-')
        return Pair(range1Start.toInt()..range1End.toInt(), range2Start.toInt()..range2End.toInt())
    }


    fun part1(input: List<String>): Int {
        return input
            .map { toRanges(it) }
            .count {
                (it.first.first >= it.second.first && it.first.last <= it.second.last) // first range within second
                        || (it.second.first >= it.first.first && it.second.last <= it.first.last) // second range within first
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { toRanges(it) }
            .count {
                it.first.first <= it.second.last && it.first.last >= it.second.first
            }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
