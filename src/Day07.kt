import java.util.*

data class FileElement(val name: String, val length: Long)
data class Dir(
    val name: String,
    val parent: Dir? = null,
    val children: MutableList<Dir> = mutableListOf(),
    val files: MutableList<FileElement> = mutableListOf(),
    var size: Long = 0
) {

    override fun toString(): String {
        return name
    }
}


class FileSystem {

    private var root: Dir? = null
    private var currentDirectory: Dir? = null
    var size: Long = 0

    fun cd(value: String) {
        currentDirectory = when (value) {
            "/" -> {

                if (root == null)
                    root = Dir("/", null, mutableListOf(), mutableListOf())
                root
            }
            ".." -> {
                currentDirectory?.parent
            }
            else -> {
                var newDir: Dir? = null
                if (currentDirectory != null) {
                    for (d in currentDirectory?.children!!) {
                        if (d.name == value) {
                            newDir = d
                            break
                        }
                    }
                }
                newDir
            }
        }
    }

    fun addDirectory(dir: String) {
        val d = Dir(dir, currentDirectory)
        currentDirectory?.children?.add(d)
    }

    fun addFile(file: FileElement) {
        currentDirectory?.files?.add(file)

        var d = currentDirectory

        while (d != null) {
            d.size += file.length
            d = d.parent
        }
    }

    fun sizeAtMost(s: Long): Long {
        val queue = LinkedList<Dir>()
        var sum: Long = 0
        if (root != null) {
            queue.add(root!!)
            while (!queue.isEmpty()) {
                val head = queue.removeFirst()
                if (head.size <= s)
                    sum += head.size
                queue.addAll(head.children)
            }
        }
        return sum
    }

    fun freeSpace(total: Long, required: Long): Long {
        var candidate = total
        if (root != null) {
            val available = total - root?.size!!
            val toFree = required - available

            val queue = LinkedList<Dir>()
            if (root != null) {
                queue.add(root!!)
                while (!queue.isEmpty()) {
                    val head = queue.removeFirst()
                    if (toFree < head.size)
                        candidate = candidate.coerceAtMost(head.size)

                    queue.addAll(head.children)
                }
            }
        }
        return candidate
    }
}


fun main() {
    fun fs(input: List<String>): FileSystem {
        val fs = FileSystem()
        for (line in input) {
            if (line.startsWith("$")) {
                if (line.startsWith("$ cd")) {
                    val dir = line.substringAfterLast(" ")
                    fs.cd(dir)
                }
            } else if (line.startsWith("dir")) {
                val dir = line.substringAfterLast(" ")
                fs.addDirectory(dir)
            } else {
                val (size, name) = line.split(" ")
                fs.addFile(FileElement(name, size.toLong()))
            }
        }
        return fs
    }


    fun part1(fs: FileSystem): Long {
        return fs.sizeAtMost(100000L)
    }


    fun part2(fs: FileSystem): Long {
        return fs.freeSpace(70000000L, 30000000L)
    }

    check(part1(fs(readInput("Day07_test"))) == 95437L)
    println(part1(fs(readInput("Day07"))))

    check(part2(fs(readInput("Day07_test"))) == 24933642L)
    println(part2(fs(readInput("Day07"))))
}
