package com.submission.news.utils.preference

import android.content.Context
import android.content.SharedPreferences

class Preference(context:Context) {
    private var sharedPreferences: SharedPreferences
    private var PREF_USER = "PREF_USER"
    var editor : SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun sessionLogin(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }
    fun getSessionLogin(key: String) : Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putString(key: String, value: String?) {
        editor.putString(key, value!!)
            .apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
            .apply()
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
            .apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun clear() {
        editor.clear().apply()
    }
}