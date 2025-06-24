package se.tp21.karabiner

import org.junit.jupiter.api.Test
import se.tp21.karabiner.snippets.rules.SnippetRules
import se.tp21.karabiner.snippets.rules.toComplexModifications
import se.tp21.karabiner.snippets.utils.decode
import se.tp21.karabiner.snippets.utils.encode
import kotlin.test.assertEquals

class SnippetRulesTest {
    @Test
    fun `snippets rules are correct`() {
        assertEquals(
            expected = snippetRulesComplexModificationJson.trimAll(),
            actual = snippetRulesJson.decode<SnippetRules>().toComplexModifications().encode().trimAll(),
        )
    }

    private val snippetRulesJson = javaClass.getResource("/testSnippetRules.json")!!.readText()
    private val snippetRulesComplexModificationJson =
        javaClass.getResource("/testSnippetRulesComplexModification.json")!!.readText()
}

private fun String.trimAll() = trimStart().trimEnd().trimIndent()
