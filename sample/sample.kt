import java.io.*
import java.util.*

/*
Writes a 4 GB text file. Each line of the file contains
a random word, which is 64 chars long. The line separator
is '\n'.

To Build: /opt/kotlinc/bin/kotlinc sample.kt -include-runtime -d sample.jar
To Run: java -jar sample.jar

Note: If you don't like Java, there try the incredibly slow Python version.
Author: Fidel Dahan
*/

val FILE_SIZE = 4L * 1024 * 1024 * 1024  // 4 GB
val ALPHABET64 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789__"
val WORD_COUNT = FILE_SIZE.div(ALPHABET64.length + 1)

class Generator(
    val buffer: MutableList<Char> = ArrayList(ALPHABET64.toList()),
    val random: Random = Random(1)
) {
  fun next_word(): MutableList<Char> {
    Collections.shuffle(buffer, random)
    return buffer
  }
}

fun write(generator: Generator, writer: BufferedWriter) {
  for (i in 1..WORD_COUNT) {
    val word = generator.next_word()
    for (c in word) {
      writer.write(c.toInt())
    }
    writer.write('\n'.toInt())
  }
}

fun main(args: Array<String>) {
  val generator = Generator()
  File("sample.txt").bufferedWriter().use {
    write(generator, it)
  }
}
