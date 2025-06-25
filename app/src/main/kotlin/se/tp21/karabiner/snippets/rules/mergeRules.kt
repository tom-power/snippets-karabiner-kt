package se.tp21.karabiner.snippets.rules

import sh.kau.karabiner.Condition
import sh.kau.karabiner.Condition.VariableIfCondition
import sh.kau.karabiner.Condition.VariableUnlessCondition
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.KeyCode
import sh.kau.karabiner.Manipulator

fun mergeRules(from: List<KarabinerRule>): List<KarabinerRule> {
    val manipulators = from.mapNotNull { it.manipulators }.flatten()

    val withConditionsFromDuplicatesNegated =
        findDuplicates(manipulators.filterNot { it.from.keyCode == terminalManipulatorKeyCode })
            .flatMap { duplicates -> withConditionsFromOthersNegated(duplicates) }

    return from.map { rule ->
        KarabinerRule(
            description = rule.description,
            manipulators =
                rule.manipulators?.map { manipulator ->
                    withConditionsFromDuplicatesNegated.singleOrNull { it.matches(manipulator) } ?: manipulator
                }
        )
    }
}

private val terminalManipulatorKeyCode = KeyCode.Spacebar

private fun Manipulator.matches(other: Manipulator): Boolean =
    this.from.keyCode == other.from.keyCode
        && this.description == other.description

private fun findDuplicates(from: List<Manipulator>): List<List<Manipulator>> =
    from
        .groupBy { it.from.keyCode }
        .filter { it.value.size > 1 }
        .map { it.value }

private fun withConditionsFromOthersNegated(from: List<Manipulator>): List<Manipulator> {
    val conditionsAtIndex = mutableMapOf<Int, List<Condition>?>()
    from.mapIndexed { index, manipulator ->
        conditionsAtIndex.put(index, manipulator.conditions)
    }
    return from.mapIndexed { index, manipulator ->
        val conditions = manipulator.conditions.orEmpty()
        val otherConditions = otherConditions(conditionsAtIndex, index)
        manipulator.copy(conditions = (conditions + negate(otherConditions)))
    }
}

private fun otherConditions(
    conditionsAtIndex: MutableMap<Int, List<Condition>?>,
    index: Int
): List<Condition> =
    conditionsAtIndex
        .filter { it.key != index }
        .mapNotNull { it.value }
        .flatten()

private fun negate(conditions: List<Condition>): List<VariableUnlessCondition> =
    conditions
        .filterIsInstance<VariableIfCondition>()
        .map { VariableUnlessCondition(name = it.name, value = it.value) }

