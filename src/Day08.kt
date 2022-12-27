fun main() {

    fun parseArray(input: List<String>): Array<IntArray> {
        val row = input.size
        val column = input[0].length
        val array = Array(row) { IntArray(column) }
        for (r in 0 until row) {
            val line = input[r]
            for (c in 0 until column) {
                array[r][c] = line[c].digitToInt()
            }
        }
        return array
    }

    fun part1(input: Array<IntArray>): Int {
        val row = input.size
        val column = input[0].size
        val field = Array(row) { BooleanArray(column) }


        for (r in 0 until row) {
            var max = Integer.MIN_VALUE
            for (c in 0 until column) {
                if (input[r][c] > max) {
                    field[r][c] = true
                    max = input[r][c]
                }
            }
        }

        for (c in 0 until column) {
            var max = Integer.MIN_VALUE
            for (r in 0 until row) {
                if (input[r][c] > max) {
                    field[r][c] = true
                    max = input[r][c]
                }
            }
        }

        for (r in 0 until row) {
            var max = Integer.MIN_VALUE
            for (c in column - 1 downTo 0) {
                if (input[r][c] > max) {
                    field[r][c] = true
                    max = input[r][c]
                }
            }
        }

        for (c in 0 until column) {
            var max = Integer.MIN_VALUE
            for (r in row - 1 downTo 0) {
                if (input[r][c] > max) {
                    field[r][c] = true
                    max = input[r][c]
                }
            }
        }

        var sum = 0
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (field[i][j])
                    sum++
            }
        }
        return sum
    }

    fun getScenicScore(r: Int, c: Int, input: Array<IntArray>): Long {
        val v = input[r][c]
        val row = input.size
        val column = input[0].size
        var left = 0L

        for (i in c  - 1 downTo 0) {
            left++
            if (input[r][i] >= v)
                break
        }
        var right = 0L
        for (i in c + 1  until column) {
            right++
            if (input[r][i] >= v)
                break
        }

        var top = 0L
        for (i in r - 1 downTo 0) {
            top++
            if (input[i][c] >= v)
                break
        }

        var down = 0L
        for (i in r + 1 until row) {
            down++
            if (input[i][c] >= v)
                break
        }

        return left * right * top * down
    }

    fun part2(input: Array<IntArray>): Long {
        val row = input.size
        val column = input[0].size
        var score = 0L
        for (r in 0 until row) {
            for (c in 0 until column) {
                val currentScore = getScenicScore(r, c, input)
                score = score.coerceAtLeast(currentScore)
            }
        }
        return score
    }

    check(part1(parseArray(readInput("Day08_test"))) == 21)
    println(part1(parseArray(readInput("Day08"))))

    check(part2(parseArray(readInput("Day08_test"))) == 8L)
    println(part2(parseArray(readInput("Day08"))))
}
