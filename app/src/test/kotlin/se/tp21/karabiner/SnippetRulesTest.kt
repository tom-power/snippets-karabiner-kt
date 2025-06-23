package se.tp21.karabiner

import org.junit.jupiter.api.Test
import se.tp21.karabiner.utils.snippetJsonEncoder
import sh.kau.karabiner.jsonEncoder
import kotlin.test.assertEquals

class SnippetRulesTest {
    @Test
    fun `snippets rules are correct`() {
        assertEquals(
            expected = snippetRulesComplexModificationJson.trimAll(),
            actual = jsonEncoder(snippetsWith(snippetJsonEncoder(snippetRulesJson))).trimAll(),
        )
    }

    private val snippetRulesJson = javaClass.getResource("/testSnippetRules.json")!!.readText()
    private val snippetRulesComplexModificationJson =
        javaClass.getResource("/testSnippetRulesComplexModification.json")!!.readText()
}

private fun String.trimAll() = trimStart().trimEnd().trimIndent()
