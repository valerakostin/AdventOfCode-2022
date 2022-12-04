fun main() {

    fun readData(input: List<String>): List<Int> {
        val nums = mutableListOf<Int>()
        var currentSum = 0
        for (line in input) {
            if (line.trim() == "") {
                nums.add(currentSum)
                currentSum = 0
            } else {
                currentSum += Integer.parseInt(line)
            }
        }
        nums.add(currentSum)
        nums.sortDescending()
        return nums
    }

    val testInput = readInput("Day01_test")
    val testData = readData(testInput)
    check(testData[0] == 24000)

    val input = readInput("Day01")
    val data = readData(input)
    println(data[0])
    check(testData[0] + testData[1] + testData[2] == 45000)
    println(data[0] + data[1] + data[2])
}
