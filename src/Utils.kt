import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()
fun readInts(name: String) = readInputWithTransform(name, { it.toInt() })

fun <T> readInputWithTransform(name: String, transform: (String) -> T, filter: (T) -> Boolean = { true }) =
    File("src", "$name.txt").readLines().map { transform(it) }.filter(filter).toList()
/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
