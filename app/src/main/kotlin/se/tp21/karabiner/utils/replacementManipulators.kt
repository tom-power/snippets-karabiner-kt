package se.tp21.karabiner.utils

import kotlinx.serialization.json.JsonPrimitive
import se.tp21.karabiner.utils.ConstTo.delete
import sh.kau.karabiner.*
import java.util.*

fun replacementManipulators(keyCodes: List<KeyCode>, replacement: List<To>): List<Manipulator> {
    val variableName = keyCodes.joinToString("") { it.name.lowercase()  }
    var lastVariableValue: String

    val first = keyCodes.first().let { keyCode ->
        val keyCodeName = keyCode.name.first().lowercase()
        Manipulator(
            from = From(keyCode),
            to = setVariableAndLast(
                keyCode = keyCode,
                variableName = variableName,
                variableValue = keyCodeName,
            ),
            description = variableName,
        ).also { lastVariableValue = keyCodeName }
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
                        variableKey = variableName,
                        variableValue = lastVariableValue,
                    ),
                    to = setVariableAndLast(
                        keyCode = keyCode,
                        variableName = variableName,
                        variableValue = thisVariableValue,
                    ),
                    description = variableName,
                )
            ).also { lastVariableValue = thisVariableValue }
        }

    val last = Manipulator(
        from = From(KeyCode.Spacebar),
        conditions = ifVariableAndLastConditions(
            last = keyCodes.last(),
            variableKey = variableName,
            variableValue = lastVariableValue,
        ),
        to = unsetVar(variableName) + keyCodes.map { delete } + replacement,
        description = variableName,
    )

    return firstAndMiddle + listOf(last)

}

private fun setVariableAndLast(
    keyCode: KeyCode,
    variableName: String,
    variableValue: String,
): List<To> =
    listOf(
        To(keyCode = keyCode),
        To(setVariable = setVariable(variableName, variableValue)),
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
        name = VariableKeys.lastKeyCode,
        value = keyCode.name.lowercase(Locale.getDefault())
    )

private fun setVariable(name: String, value: String) =
    SetVariable(
        name = name,
        value = JsonPrimitive(value)
    )

