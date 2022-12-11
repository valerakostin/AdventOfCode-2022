import java.util.*

fun main() {
    data class Move(val count: Int, val from: Int, val to: Int)

    fun parseStacks(input: List<String>): List<Stack<String>> {

        val lastLine = input.last()
        val stackCount = (lastLine.length + 2) / 4
        val stacks = mutableListOf<Stack<String>>()

        repeat(stackCount) { stacks.add(Stack()) }

        for (i in input.size - 2 downTo 0) {
            val line = input[i] + " "
            for (offset in 0..line.length - 4 step 4) {
                val v = line.substring(offset, offset + 4).trim()
                if (v.isNotBlank())
                    stacks[offset / 4].push(v.removeSurrounding("[", "]"))
            }
        }
        return stacks
    }

    fun parseInput(input: List<String>): Pair<List<Stack<String>>, List<Move>> {
        val stackLines = mutableListOf<String>()
        for (iIter in input.withIndex()) {
            if (iIter.value.trim().isBlank()) {
                val stacks = parseStacks(stackLines)
                val index = iIter.index + 1
                val pattern = """move (\d+) from (\d+) to (\d+)""".toRegex()
                val moves = mutableListOf<Move>()

                for (idx in index until input.size) {
                    val moveLine = input[idx]
                    val match = pattern.find(moveLine)
                    val (repeatCount, from, to) = match!!.destructured
                    moves.add(Move(
                        repeatCount.toInt(),
                        from.toInt() - 1,
                        to.toInt() - 1))
                }
                return Pair(stacks, moves)
            } else {
                stackLines.add(iIter.value)
            }
        }
        return Pair(listOf(), listOf())
    }

    fun processStack(
        stacks: List<Stack<String>>,
        moves: List<Move>,
        algorithm: (List<Stack<String>>, Move) -> Unit
    ): String {
        moves.forEach { algorithm.invoke(stacks, it) }
        return buildString {
            for (stack in stacks) {
                append(stack.peek())
            }
        }
    }

    fun part1(input: List<String>): String {
        val pair = parseInput(input)
        return processStack(
            pair.first,
            pair.second) { s, move ->
            repeat(move.count) {
                s[move.to].push(s[move.from].pop())
            }
        }
    }

    fun part2(input: List<String>): String {
        val pair = parseInput(input)
        return processStack(
            pair.first,
            pair.second) { stacks, move ->
            val tmp = mutableListOf<String>()
            repeat(move.count) {
                tmp.add(stacks[move.from].pop())
            }
            repeat(move.count) {
                stacks[move.to].push(tmp.removeLast())
            }
        }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    println(part1(input))

    check(part2(testInput) == "MCD")
    println(part2(input))
}
