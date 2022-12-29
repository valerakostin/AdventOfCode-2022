import kotlin.math.abs

data class Loc(val x: Int, val y: Int) {
    fun adjust(tail: Loc): Loc {
        val diffX = x - tail.x
        val diffY = y - tail.y

        if ((abs(diffX) == 1 && diffY == 0) ||
            (diffX == 0 && abs(diffY) == 1) ||
            (diffX == 0 && diffY == 0) ||
            (abs(diffX) == 1 && abs(diffY) == 1)
        )
            return tail

        if (abs(diffX) == 2 && diffY == 0) {
            return if (diffX > 0)
                Loc(tail.x + 1, tail.y)
            else
                Loc(tail.x - 1, tail.y)
        } else if (abs(diffY) == 2 && diffX == 0) {
            return if (diffY > 0)
                Loc(tail.x, tail.y + 1)
            else
                Loc(tail.x, tail.y - 1)
        } else { // diagonal
            if (diffX == 2 && diffY == 1 || diffX == 1 && diffY == 2 || diffX == 2 && diffY == 2)
                return Loc(tail.x + 1, tail.y + 1)
            else if (diffX == -2 && diffY == 1 || diffX == -1 && diffY == 2 || diffX == -2 && diffY == 2)
                return Loc(tail.x - 1, tail.y + 1)
            else if (diffX == 1 && diffY == -2 || diffX == 2 && diffY == -1 || diffX == 2 && diffY == -2)
                return Loc(tail.x + 1, tail.y - 1)
            else if (diffX == -2 && diffY == -1 || diffX == -1 && diffY == -2 || diffX == -2 && diffY == -2)
                return Loc(tail.x - 1, tail.y - 1)
        }
        return tail
    }
}

fun main() {

    fun computeTailPosition(input: List<String>, components: List<Loc>): Int {
        val visited = mutableSetOf<Loc>()
        val items = mutableListOf<Loc>()

        items.addAll(components)

        for (move in input) {
            val (dir, step) = move.split(" ")
            val diff = when (dir) {
                "R" -> Loc(1, 0)
                "L" -> Loc(-1, 0)
                "U" -> Loc(0, 1)
                else -> Loc(0, -1)
            }

            repeat(step.toInt()) {

                val tmp = mutableListOf<Loc>()

                var head = items[0]
                head = Loc(head.x + diff.x, head.y + diff.y)
                tmp.add(head)

                for (t in 1 until items.size) {
                    val next = tmp.last().adjust(items[t])
                    tmp.add(next)
                }

                items.clear()
                items.addAll(tmp)
                visited.add(items.last())
            }
        }
        return visited.size
    }

    fun part1(input: List<String>): Int {
        return computeTailPosition(input, listOf(Loc(0, 0), Loc(0, 0)))
    }

    fun part2(input: List<String>): Int {
        val items = mutableListOf<Loc>()
        repeat(10) {
            items.add(Loc(0, 0))
        }
        return computeTailPosition(input, items)
    }


    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)

    val input = readInput("Day09")
    println(part1(input))

    check(part2(testInput) == 1)
    check(part2(listOf("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20")) == 36)
    println(part2(input))
}
