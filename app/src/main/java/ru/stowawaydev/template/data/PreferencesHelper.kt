package ru.stowawaydev.template.data

import android.content.Context
import android.content.SharedPreferences

/**
 * template header (replace it)
 */

class PreferencesHelper(context: Context) {

    companion object {
        private const val PREFS_NAME = "template_prefs"

        private const val EXAMPLE_VALUE = "example_value"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveExampleField(value: String, inBackground: Boolean = true) {
        put(EXAMPLE_VALUE, value, inBackground)
    }

    private fun <T> put(key: String, value: T, inBackground: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit()
        when (value) {
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is String -> editor.putString(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }
        perform(editor, inBackground)
    }

    private fun putStringSet(key: String, value: Set<String>, inBackground: Boolean) {
        val editor: SharedPreferences.Editor = prefs.edit().putStringSet(key, value)
        perform(editor, inBackground)
    }

    private fun perform(editor: SharedPreferences.Editor, inBackground: Boolean) {
        if (inBackground) editor.apply() else editor.commit()
    }
}
