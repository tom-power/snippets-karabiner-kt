package se.tp21.karabiner.snippets.rules

import se.tp21.karabiner.snippets.utils.Const.delete
import se.tp21.karabiner.snippets.utils.VariableKeys.snippetKeys
import se.tp21.karabiner.snippets.utils.ifSnippetKeysAndLastKey
import se.tp21.karabiner.snippets.utils.setSnippetKeysAndLastKey
import sh.kau.karabiner.*

class ReplacementManipulators(
    private val keyCodes: List<KeyCode>,
    private val replacement: List<To>
) : () -> List<Manipulator> {
    private val description = keyCodes.joinToString("") { it.name.lowercase() }
    private var lastSnippet: String = ""

    override fun invoke(): List<Manipulator> {
        val first =
            keyCodes.first().let { keyCode ->
                val keyCodeName = keyCode.name.first().lowercase()
                Manipulator(
                    from = From(keyCode),
                    to = setSnippetKeysAndLastKey(
                        lastKey = keyCode,
                        snippetKeys = keyCodeName,
                    ),
                    description = description,
                ).also { lastSnippet = keyCodeName }
            }

        val firstAndMiddle =
            keyCodes.drop(1).fold(listOf(first)) { acc, keyCode ->
                val last = acc.last().from.keyCode!!
                val thisSnippetKeys = lastSnippet + keyCode.name.lowercase()
                acc + listOf(
                    Manipulator(
                        from = From(keyCode),
                        conditions = ifSnippetKeysAndLastKey(
                            lastKey = last,
                            snippetKeys = lastSnippet,
                        ),
                        to = setSnippetKeysAndLastKey(
                            lastKey = keyCode,
                            snippetKeys = thisSnippetKeys,
                        ),
                        description = description,
                    )
                ).also { lastSnippet = thisSnippetKeys }
            }

        val last = Manipulator(
            from = From(KeyCode.Spacebar),
            conditions = ifSnippetKeysAndLastKey(
                lastKey = keyCodes.last(),
                snippetKeys = lastSnippet,
            ),
            to = unsetVar(snippetKeys) + keyCodes.map { delete } + replacement,
            description = description,
        )

        return firstAndMiddle + listOf(last)
    }

}


