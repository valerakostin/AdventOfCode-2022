fun main() {
    val lost = 0
    val draw = 3
    val win = 6

    val rock = 1
    val paper = 2
    val scissors = 3

    val map = mapOf(
        "A X" to rock + draw,
        "A Y" to paper + win,
        "A Z" to scissors + lost,
        "B X" to rock + lost,
        "B Y" to paper + draw,
        "B Z" to scissors + win,
        "C X" to rock + win,
        "C Y" to paper + lost,
        "C Z" to scissors + draw
    )

    val map2 = mapOf(
        "A X" to lost + scissors,
        "A Y" to draw + rock,
        "A Z" to win + paper,
        "B X" to lost + rock,
        "B Y" to draw + paper,
        "B Z" to win + scissors,
        "C X" to lost + paper,
        "C Y" to draw + scissors,
        "C Z" to win + rock
    )

    fun part1(input: List<String>): Int = input.sumOf { map.getOrDefault(it, 0) }


    fun part2(input: List<String>): Int = input.sumOf { map2.getOrDefault(it, 0) }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    check(part2(testInput) == 12)
    println(part2(input))
}
