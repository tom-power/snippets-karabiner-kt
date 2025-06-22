package se.tp21.karabiner.utils

import kotlinx.serialization.json.JsonPrimitive
import se.tp21.karabiner.utils.ConstTo.delete
import sh.kau.karabiner.*
import java.util.*

fun replacementManipulators(keyCodes: List<KeyCode>, replacement: List<To>): List<Manipulator> {
    val variableKey = keyCodes.joinToString("") { it.name.lowercase()  }
    var lastVariableValue = ""

    val first = keyCodes.first().let { keyCode ->
        val variableValue = keyCode.name.first().lowercase()
        Manipulator(
            from = From(keyCode),
            to = setVariableAndLast(
                keyCode = keyCode,
                variableKey = variableKey,
                variableValue = variableValue,
            )
        ).also { lastVariableValue = variableValue }
    }

    val firstAndMiddle =
        keyCodes.drop(1).fold(listOf(first)) { acc, keyCode ->
            val last = acc.last().from.keyCode!!
            val thisVariableValue = lastVariableValue + keyCode.name.lowercase()
            acc + listOf(
                Manipulator(
                    from = From(keyCode),
                    conditions = ifVariableAndLastConditions(
                        last = last,
                        variableKey = variableKey,
                        variableValue = lastVariableValue,
                    ),
                    to = setVariableAndLast(
                        keyCode = keyCode,
                        variableKey = variableKey,
                        variableValue = thisVariableValue,
                    )
                )
            ).also { lastVariableValue = thisVariableValue }
        }

    val last = Manipulator(
        from = From(KeyCode.Spacebar),
        conditions = ifVariableAndLastConditions(
            last = keyCodes.last(),
            variableKey = variableKey,
            variableValue = lastVariableValue,
        ),
        to = unsetVar(variableKey) + keyCodes.map { delete } + replacement
    )

    return firstAndMiddle + listOf(last)

}

private fun setVariableAndLast(
    keyCode: KeyCode,
    variableKey: String,
    variableValue: String,
): List<To> =
    listOf(
        To(keyCode = keyCode),
        To(setVariable = setVariable(variableKey, variableValue)),
        To(setVariable = setLastKeyCodeVariable(keyCode))
    )

private fun ifVariableAndLastConditions(
    last: KeyCode,
    variableKey: String,
    variableValue: String,
): List<Condition.VariableIfCondition> =
    listOf(
        Condition.VariableIfCondition(VariableKeys.lastKeyCode, JsonPrimitive(last.name.lowercase(Locale.getDefault()))),
        Condition.VariableIfCondition(variableKey, JsonPrimitive(variableValue)),
    )

fun setLastKeyCodeVariable(keyCode: KeyCode) =
    setVariable(
        key = VariableKeys.lastKeyCode,
        value = keyCode.name.lowercase(Locale.getDefault())
    )

private fun setVariable(key: String, value: String) = SetVariable(key, JsonPrimitive(value))

