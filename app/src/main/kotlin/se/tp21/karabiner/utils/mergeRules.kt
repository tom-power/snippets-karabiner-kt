package se.tp21.karabiner.utils

import sh.kau.karabiner.Condition
import sh.kau.karabiner.Condition.VariableIfCondition
import sh.kau.karabiner.Condition.VariableUnlessCondition
import sh.kau.karabiner.KarabinerRule
import sh.kau.karabiner.Manipulator

fun mergeRules(from: List<KarabinerRule>): List<KarabinerRule> {
    val manipulators = from.mapNotNull { it.manipulators }.flatten()

    val duplicatesNegated = duplicatesNegated(manipulators)

    return from.map {
        KarabinerRule(
            description = it.description,
            manipulators =
                it.manipulators
                    ?.map { manipulator ->
                        duplicatesNegated.match(manipulator) ?: manipulator
                    }
        )
    }
}

private fun List<Manipulator>.match(manipulator: Manipulator): Manipulator? =
    this.singleOrNull {
        it.from.keyCode == manipulator.from.keyCode
            && it.description == manipulator.description
    }

fun duplicatesNegated(from: List<Manipulator>): List<Manipulator> =
    duplicateReplacementManipulators(from)
        .flatMap { withOtherConditionsNegated(it) }

private fun duplicateReplacementManipulators(from: List<Manipulator>): List<List<Manipulator>> =
    from
        .groupBy { it.from.keyCode }
        .filter { it.value.size > 1 }
        .map { it.value }

private fun withOtherConditionsNegated(from: List<Manipulator>): List<Manipulator> {
    val conditionsAtIndex = mutableMapOf<Int, List<Condition>?>()
    from.mapIndexed { index, manipulator ->
        conditionsAtIndex.put(index, manipulator.conditions)
    }
    return from.mapIndexed { index, manipulator ->
        val currentConditions = manipulator.conditions.orEmpty()
        val otherConditions = otherConditions(conditionsAtIndex, index)
        manipulator.copy(conditions = (currentConditions + negate(otherConditions)))
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

