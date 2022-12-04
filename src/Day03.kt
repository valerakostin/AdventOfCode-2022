fun main() {

    fun computePriority(c: Char): Int {
        return if (Character.isUpperCase(c))
            c.lowercaseChar().code - 96 + 26
        else
            c.code - 96
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val m = line.length / 2
            val set = line.subSequence(0, m).toSet()
            for (i in m..line.length) {
                if (line[i] in set) {
                    sum += computePriority(line[i])
                    break
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var s = 0
        for (chunk in input.chunked(3)) {
            val set1 = chunk[0].toSet()
            val set2 = chunk[1].toSet()

            for (c in chunk[2]) {
                if (c in set1 && c in set2) {
                    s += computePriority(c)
                    break
                }
            }
        }
        return s
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))

    check(part2(testInput) == 70)
    println(part2(input))
}
