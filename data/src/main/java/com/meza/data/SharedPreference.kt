package com.meza.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceDataSource (private val application: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences("AUTH_DATA", 0)
    }
    fun getString(key: String, default: String): String {
        return sharedPreferences.get(key, default)
    }
    fun putString(key: String, value: String?) {
        sharedPreferences.put(key, value)
    }
}

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when (T::class) {
        String::class -> return this.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                @Suppress("UNCHECKED_CAST")
                return this.getStringSet(key, defaultValue as Set<String>) as T
            }
        }
    }
    return defaultValue
}

inline fun <reified T> SharedPreferences.put(key: String, value: T): T {
    val editor = this.edit()
    when (T::class) {
        String::class -> editor.putString(key, value as String)
        else -> {
            if (value is Set<*>) {
                @Suppress("UNCHECKED_CAST")
                editor.putStringSet(key, value as Set<String>)
            }
        }
    }
    editor.apply()
    return value
}
