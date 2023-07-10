package com.example.detection.Utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "DetectionSharedPreferences", Activity.MODE_PRIVATE
    )
    private companion object {
        const val KEY_SHOW_INTRO = "show_intro"
    }
    var showIntroBefore: Boolean
        get() = getBoolean(KEY_SHOW_INTRO, false)
        set(value) = setBoolean(KEY_SHOW_INTRO, value)


    private  val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    private fun getLong(key: String, defaultValue: Long = 0): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    private fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    private fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    private fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    private fun setLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }
}