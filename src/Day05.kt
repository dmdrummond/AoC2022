import java.util.*

fun main() {


    fun charPositionFor(i: Int): Int {
        return when (i) {
            1 -> 1
            else -> 4 * i - 3
        }
    }

    fun parseInput(input: List<String>): Pair<ElfStack, List<Instruction>> {
        val elfStack = mutableListOf<Stack<Char>>()
        val instructions = mutableListOf<Instruction>()

        // Parse initial stack state
        val endOfStack = input.indexOfFirst { it.isEmpty() }
        val stackNumberLine = input[endOfStack-1]

        val numberOfStacks = stackNumberLine
                .substring(1, stackNumberLine.length)
                .split("\\s+".toRegex())
                .maxOfOrNull { it.toInt() }
                ?: throw Error("Error parsing stack number")
        for(i in 1..numberOfStacks) {
            elfStack.add(Stack())
        }

        for (line in endOfStack-1 downTo 0) {
            for (i in 1..numberOfStacks) {
                val charPosition = charPositionFor(i)
                if (input[line].length >= charPosition) {
                    val char = input[line][charPositionFor(i)]
                    if (char != ' ') {
                        elfStack[i - 1].push(char)
                    }
                }
            }
        }


        //Parse instructions
        for (index in endOfStack+1 until input.size) {
            val tokens = input[index].split(" ")
            instructions.add(Instruction(tokens[1].toInt(), tokens[3].toInt(), tokens[5].toInt()))
        }

        return elfStack to instructions
    }

    fun processInstruction(stack: ElfStack, instruction: Instruction) {
        for (i in 0 until instruction.count) {
            stack[instruction.destination - 1].push(
                    stack[instruction.source - 1].pop()
            )
        }
    }

    fun processInstructionCrateMover9001(stack: ElfStack, instruction: Instruction) {
        val tempStack = Stack<Char>()
        for (i in 0 until instruction.count) {
            tempStack.push(stack[instruction.source - 1].pop())
        }
        for (i in 0 until instruction.count) {
            stack[instruction.destination - 1].push(
                    tempStack.pop()
            )
        }
    }

    fun part1(input: List<String>): String {
        val (stack, instructions) = parseInput(input)
        instructions.forEach { processInstruction(stack, it) }
        return stack.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val (stack, instructions) = parseInput(input)
        instructions.forEach { processInstructionCrateMover9001(stack, it) }
        return stack.map { it.last() }.joinToString("")
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

data class Instruction(val count: Int, val source: Int, val destination: Int)
typealias ElfStack = List<Stack<Char>>
