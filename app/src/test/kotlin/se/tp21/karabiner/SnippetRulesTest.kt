package se.tp21.karabiner

import org.junit.jupiter.api.Test
import sh.kau.karabiner.jsonEncoder

class SnippetRulesTest {

    @Test
    fun `snippets rules are correct`() {
        kotlin.test.assertEquals(
            expected = javaClass.getResource("/testSnippetRules.json")!!.readText().trimAll(),
            actual = jsonEncoder(snippets()).trimAll(),
        )
    }

}


private fun String.trimAll() = trimStart().trimEnd().trimIndent()
