package com.qxj.commondata.content

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.qxj.commondata.CommonDataApp
import kotlin.reflect.KProperty

class ConfigPreference<T> (val name: String, private val default: T) {

    private val TAG = ConfigPreference::class.java.simpleName

    private val prefs: SharedPreferences by lazy {
        CommonDataApp.INSTANCE.applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
    
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        Log.i(TAG, "调用 $name getValue")
        return getSharePreferences(name, default)
    }
    
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Log.i(TAG, "调用 $name setValue -- $value")
        putSharePreferences(name, value)
    }

    @SuppressLint("CommitPrefEdits")
    private fun putSharePreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }
        return res as T
    }
}