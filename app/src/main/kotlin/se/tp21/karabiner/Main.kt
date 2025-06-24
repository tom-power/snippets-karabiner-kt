package se.tp21.karabiner

import se.tp21.karabiner.snippets.rules.SnippetRules
import se.tp21.karabiner.utils.decode
import se.tp21.karabiner.utils.encode
import se.tp21.karabiner.utils.toComplexModifications
import java.io.File

fun main(args: Array<String>) {
    try {
        val snippetRulesJson = File(args[0]).inputStream().bufferedReader().use { it.readText() }

        val karabinerJson = snippetRulesJson.decode<SnippetRules>().toComplexModifications().encode()

        File("build/snippets-karabiner.json").let {
            it.writeText(karabinerJson)
            println("Successfully wrote json to ${it.absolutePath}")
        }
    } catch (e: Exception) {
        System.err.println("Error writing json: ${e.message}")
        e.printStackTrace()
    }
}
