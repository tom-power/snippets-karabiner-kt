package se.tp21.karabiner

import org.junit.jupiter.api.Test
import se.tp21.karabiner.utils.snippetJsonEncoder
import sh.kau.karabiner.jsonEncoder

class SnippetRulesTest {

    @Test
    fun `snippets rules are correct`() {
        val snippetRulesJson = javaClass.getResource("/testSnippetRules.json")!!.readText()
        val snippetRules = snippetJsonEncoder(snippetRulesJson)

        kotlin.test.assertEquals(
            expected = javaClass.getResource("/testSnippetRulesComplexModification.json")!!.readText().trimAll(),
            actual = jsonEncoder(snippetsWith(snippetRules)).trimAll(),
        )
    }

}


private fun String.trimAll() = trimStart().trimEnd().trimIndent()
