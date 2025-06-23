package se.tp21.karabiner

import sh.kau.karabiner.jsonEncoder
import java.io.File

fun main() {
    val karabinerJson = jsonEncoder(snippets())

    try {
        val outputFile = File("build/snippets-karabiner.json")
        outputFile.writeText(karabinerJson)
        println("Successfully wrote snippets-karabiner.json to ${outputFile.absolutePath}")
    } catch (e: Exception) {
        System.err.println("Error writing karabiner.json: ${e.message}")
        e.printStackTrace()
    }
}
