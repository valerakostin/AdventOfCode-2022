fun main() {

    fun part1and2(input: List<String>): Pair<Int, Int> {
        val pattern = """(-?\d+)-(-?\d+),(-?\d+)-(-?\d+)""".toRegex()
        var answer1 = 0
        var answer2 = 0
        for (line in input) {

            val match = pattern.find(line)
            val (r1Min, r1Max, r2Min, r2Max) = match!!.destructured
            val r1 = r1Min.toInt() .. r1Max.toInt()
            val r2 = r2Min.toInt() .. r2Max.toInt()

            if ((r1.first in r2 ||  r1.last in r2) ||
                (r2.first in r1 || r2.last in r1)) {
                answer2++
                if ((r1.first in r2 &&  r1.last in r2) ||
                    (r2.first in r1 && r2.last in r1))
                    answer1++
            }
        }
        return Pair(answer1, answer2)
    }



    val testInput = readInput("Day04_test")
    check(part1and2(testInput).first == 2)

    val input = readInput("Day04")
    println(part1and2(input).first)

    check(part1and2(testInput).second == 4)
    println(part1and2(input).second)
}
