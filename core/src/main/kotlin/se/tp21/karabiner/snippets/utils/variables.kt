package se.tp21.karabiner.snippets.utils

import kotlinx.serialization.json.JsonPrimitive
import se.tp21.karabiner.snippets.utils.VariableKeys.lastKey
import se.tp21.karabiner.snippets.utils.VariableKeys.snippetKeys
import sh.kau.karabiner.Condition
import sh.kau.karabiner.KeyCode
import sh.kau.karabiner.SetVariable
import sh.kau.karabiner.To
import java.util.*

fun setSnippetKeysAndLastKey(
    lastKey: KeyCode,
    snippetKeys: String,
): List<To> =
    listOf(
        To(setVariable = setSnippetKeys(snippetKeys)),
        To(setVariable = setLastKey(lastKey)),
        To(keyCode = lastKey),
    )

fun ifSnippetKeysAndLastKey(
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

fun setSnippetKeys(value: String) =
    setVariable(
        name = snippetKeys,
        value = value
    )

private fun setVariable(name: String, value: String) =
    SetVariable(
        name = name,
        value = JsonPrimitive(value)
    )