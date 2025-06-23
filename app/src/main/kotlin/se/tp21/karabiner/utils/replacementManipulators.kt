package se.tp21.karabiner.utils

import kotlinx.serialization.json.JsonPrimitive
import se.tp21.karabiner.utils.Const.delete
import se.tp21.karabiner.utils.VariableKeys.lastKey
import se.tp21.karabiner.utils.VariableKeys.snippetKeys
import sh.kau.karabiner.*
import java.util.*

fun replacementManipulators(keyCodes: List<KeyCode>, replacement: List<To>): List<Manipulator> {
    val description = keyCodes.joinToString("") { it.name.lowercase()  }
    var lastSnippet: String

    val first = keyCodes.first().let { keyCode ->
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

private fun setSnippetKeysAndLastKey(
    lastKey: KeyCode,
    snippetKeys: String,
): List<To> =
    listOf(
        To(keyCode = lastKey),
        To(setVariable = setSnippetKeys(snippetKeys)),
        To(setVariable = setLastKey(lastKey))
    )

private fun ifSnippetKeysAndLastKey(
    lastKey: KeyCode,
    snippetKeys: String,
): List<Condition.VariableIfCondition> =
    listOf(
        Condition.VariableIfCondition(
            name = VariableKeys.lastKey,
            value = JsonPrimitive(lastKey.name.lowercase(Locale.getDefault()))
        ),
        Condition.VariableIfCondition(
            name = VariableKeys.snippetKeys,
            value = JsonPrimitive(snippetKeys)
        ),
    )

fun setLastKey(keyCode: KeyCode) =
    setVariable(
        name = lastKey,
        value = keyCode.name.lowercase(Locale.getDefault())
    )

private fun setSnippetKeys(value: String) =
    setVariable(
        name = snippetKeys,
        value = value
    )

private fun setVariable(name: String, value: String) =
    SetVariable(
        name = name,
        value = JsonPrimitive(value)
    )

