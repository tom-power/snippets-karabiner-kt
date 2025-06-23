package se.tp21.karabiner.utils

import sh.kau.karabiner.KeyCode
import sh.kau.karabiner.To

object Const {
    val delete = To(
        keyCode = KeyCode.DeleteOrBackspace,
    )
}


object VariableKeys {
    const val lastKey = "lastKey"
    const val snippetKeys = "snippetKeys"
}