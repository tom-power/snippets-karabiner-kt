package se.tp21.karabiner

import se.tp21.karabiner.utils.snippetJsonEncoder
import sh.kau.karabiner.jsonEncoder
import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {

    val inputStream: InputStream = File(args[0]).inputStream()
    val text = inputStream.bufferedReader().use { it.readText() }

    val snippetRules  = snippetJsonEncoder(text)

    val karabinerJson = jsonEncoder(snippetsWith(snippetRules))

    try {
        val outputFile = File("build/snippets-karabiner.json")
        outputFile.writeText(karabinerJson)
        println("Successfully wrote snippets-karabiner.json to ${outputFile.absolutePath}")
    } catch (e: Exception) {
        System.err.println("Error writing karabiner.json: ${e.message}")
        e.printStackTrace()
    }
}
