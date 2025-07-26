package se.tp21.karabiner

import org.junit.jupiter.api.Test
import se.tp21.karabiner.snippets.SnippetRules
import se.tp21.karabiner.snippets.complexModificationsFrom
import se.tp21.karabiner.snippets.utils.decode
import se.tp21.karabiner.snippets.utils.encode
import kotlin.test.assertEquals

class SnippetRulesTest {
    @Test
    fun `snippets rules are correct`() {
        val expected = snippetRulesComplexModificationJson.trimAll()
        val snippetRules = snippetRulesJson.decode<SnippetRules>()

        val actual = complexModificationsFrom(snippetRules).encode().trimAll()

        assertEquals(expected, actual)
    }

    private val snippetRulesJson = javaClass.getResource("/testSnippetRules.json")!!.readText()
    private val snippetRulesComplexModificationJson =
        javaClass.getResource("/testSnippetRulesComplexModification.json")!!.readText()
}

private fun String.trimAll() = trimStart().trimEnd().trimIndent()
