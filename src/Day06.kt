fun main() {

    fun computeMessageStart(input: String, offset: Int):Int{
        return  input.windowed(offset, step = 1).takeWhile {
            it.toSet().size != offset }.count() + offset
    }

    fun part1(input: String): Int {
        return computeMessageStart(input, 4)
    }

    fun part2(input: String): Int {
        return computeMessageStart(input, 14)
    }


    check(part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 7)
    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)

    val input = readInput("Day06")[0]
    println(part1(input))

    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
    check(part2("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
    check(part2("nppdvjthqldpwncqszvftbrmjlhg") == 23)
    check(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
    check(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

    println(part2(input))
}
