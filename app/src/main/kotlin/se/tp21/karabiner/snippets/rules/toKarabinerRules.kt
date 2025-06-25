package se.tp21.karabiner.snippets.rules

import sh.kau.karabiner.*

fun toKarabinerRules(rules: SnippetRules): List<KarabinerRule> =
    rules.rules.map { rule ->
        SnippetKarabinerRuleFor(
            keyCodes = rule.keys.map { it.toKeyCode() },
            replacement = rule.replacement.map { it.toTo() },
            desc = rule.description
        ).invoke()
    }

private fun Char.toTo(): To =
    try {
        this.toKeyCode().let(::To)
    } catch (e: Exception) {
        this.toOtherTo() ?: error("Unknown to: $this")
    }

private fun Char.toOtherTo(): To? =
    when (this) {
        ' ' -> To(KeyCode.Spacebar)
        '.' -> To(KeyCode.Period)
        '1' -> To(KeyCode.Num1)
        '2' -> To(KeyCode.Num2)
        '3' -> To(KeyCode.Num3)
        '4' -> To(KeyCode.Num4)
        '5' -> To(KeyCode.Num5)
        '6' -> To(KeyCode.Num6)
        '7' -> To(KeyCode.Num7)
        '8' -> To(KeyCode.Num8)
        '9' -> To(KeyCode.Num9)
        '0' -> To(KeyCode.Num0)
        '\n' -> To(KeyCode.ReturnOrEnter)
        '\u001B' -> To(KeyCode.Escape) // ESC character
        '\b' -> To(KeyCode.DeleteOrBackspace)
        '\u007F' -> To(KeyCode.DeleteForward)
        '\t' -> To(KeyCode.Tab)
        '-' -> To(KeyCode.Hyphen)
        '=' -> To(KeyCode.EqualSign)
        '[' -> To(KeyCode.OpenBracket)
        ']' -> To(KeyCode.CloseBracket)
        '\\' -> To(KeyCode.Backslash)
        ';' -> To(KeyCode.Semicolon)
        '\'' -> To(KeyCode.Quote)
        '`' -> To(KeyCode.GraveAccentAndTilde)
        ',' -> To(KeyCode.Comma)
        '/' -> To(KeyCode.Slash)
        '|' -> To(KeyCode.NonUsBackslash)
        '!' -> To(KeyCode.Num1, modifiers = listOf(ModifierKeyCode.LeftShift))
        '@' -> To(KeyCode.Num2, modifiers = listOf(ModifierKeyCode.LeftShift))
        '€' -> To(KeyCode.Num2, modifiers = listOf(ModifierKeyCode.LeftAlt))
        '£' -> To(KeyCode.Num3, modifiers = listOf(ModifierKeyCode.LeftShift))
        '#' -> To(KeyCode.Num3, modifiers = listOf(ModifierKeyCode.LeftAlt))
        '$' -> To(KeyCode.Num4, modifiers = listOf(ModifierKeyCode.LeftShift))
        '%' -> To(KeyCode.Num5, modifiers = listOf(ModifierKeyCode.LeftShift))
        '^' -> To(KeyCode.Num6, modifiers = listOf(ModifierKeyCode.LeftShift))
        '&' -> To(KeyCode.Num7, modifiers = listOf(ModifierKeyCode.LeftShift))
        '*' -> To(KeyCode.Num8, modifiers = listOf(ModifierKeyCode.LeftShift))
        '(' -> To(KeyCode.Num9, modifiers = listOf(ModifierKeyCode.LeftShift))
        ')' -> To(KeyCode.Num0, modifiers = listOf(ModifierKeyCode.LeftShift))
        '_' -> To(KeyCode.Hyphen, modifiers = listOf(ModifierKeyCode.LeftShift))
        '+' -> To(KeyCode.EqualSign, modifiers = listOf(ModifierKeyCode.LeftShift))
        '{' -> To(KeyCode.OpenBracket, modifiers = listOf(ModifierKeyCode.LeftShift))
        '}' -> To(KeyCode.CloseBracket, modifiers = listOf(ModifierKeyCode.LeftShift))
        ':' -> To(KeyCode.Semicolon, modifiers = listOf(ModifierKeyCode.LeftShift))
        '"' -> To(KeyCode.Quote, modifiers = listOf(ModifierKeyCode.LeftShift))
        '<' -> To(KeyCode.Comma, modifiers = listOf(ModifierKeyCode.LeftShift))
        '>' -> To(KeyCode.Period, modifiers = listOf(ModifierKeyCode.LeftShift))
        '?' -> To(KeyCode.Slash, modifiers = listOf(ModifierKeyCode.LeftShift))
        '~' -> To(KeyCode.GraveAccentAndTilde, modifiers = listOf(ModifierKeyCode.LeftShift))
        else -> null
    }

private fun Char.toKeyCode(): KeyCode =
    KeyCode.from(this.toString())
