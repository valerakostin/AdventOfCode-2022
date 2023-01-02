fun main() {


    fun isCheckPoint(cycle: Int): Boolean {
        return cycle == 20 || cycle == 60 || cycle == 100 ||
                cycle == 140 || cycle == 180 || cycle == 220
    }

    fun part1(input: List<String>): Int {
        var x = 1
        var sum = 0
        var cycles = 0
        for (line in input) {
            if ("noop" == line) {
                cycles++
                if (isCheckPoint(cycles))
                    sum += x * cycles
            } else if (line.startsWith("addx")) {
                val (_, num) = line.split(" ")
                val value = num.toInt()
                cycles++
                if (isCheckPoint(cycles))
                    sum += x * cycles
                cycles++
                if (isCheckPoint(cycles))
                    sum += x * cycles
                x += value
            }
        }
        return sum
    }

    fun part2(input: List<String>) {
        var x = 1
        val values = mutableSetOf<Int>()
        var cycles = 0
        for (line in input) {
            if ("noop" == line) {
                if (cycles%40 in x - 1..x + 1)
                    values.add(cycles)
                cycles++
            } else if (line.startsWith("addx")) {
                val (_, num) = line.split(" ")
                val value = num.toInt()

                if (cycles%40 in x - 1..x + 1)
                    values.add(cycles)
                cycles++

                if (cycles%40 in x - 1..x + 1)
                    values.add(cycles)
                cycles++
                x += value
            }
        }
        for (row in 0 until 6) {
            for (i in 0 until 40) {
                val symbol = if (values.contains(row * 40 + i))
                    '#' else ' '
                print(symbol)
            }
            println()
        }
    }


    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)

    val input = readInput("Day10")
    println(part1(input))

    // check(part2(testInput))
    part2(input)
}
