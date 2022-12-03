fun main() {
    fun buildList(input: List<String>): List<Int> {
        val calorieList = mutableListOf<Int>()
        var calories = 0
        input.forEach {
            if (it.isEmpty()) {
                calorieList.add(calories)
                calories = 0
            } else {
                calories += (it.toInt())
            }
        }
        calorieList.add(calories)
        return calorieList
    }

    fun part1(input: List<Int>) = input.max()
    fun part2(input: List<Int>) = input.sorted().takeLast(3).sum()

    val testInput = readInput("Day01_test")
    val parsedTestList = buildList(testInput)
    check(part1(parsedTestList) == 24000)
    check(part2(parsedTestList) == 45000)

    val input = readInput("Day01")
    val parsedList = buildList(input)
    println("Part 1: ${part1(parsedList)}")
    println("Part 2: ${part2(parsedList)}")
}
