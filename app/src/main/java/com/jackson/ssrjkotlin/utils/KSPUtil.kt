package com.jackson.ssrjkotlin.utils

import android.content.Context
import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *   2018-02-26
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * 委托实现sp
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 26
 */
class KSPUtil<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {

    private val SHARE_PREFER: String = "kssrj" //SP保存的xml文件名

    val prefs by lazy {
        Log.d("hbj", "init sp")
        context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> {
                getLong(name, default)
            }
            is String -> {
                getString(name, default)
            }
            is Float -> {
                getFloat(name, default)
            }
            is Int -> {
                getInt(name, default)
            }
            is Boolean -> {
                getBoolean(name, default)
            }

            else -> {
                throw  IllegalArgumentException("This type can't be saved into Preferences")
            }
        }
        res as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String->putString(name,value)
            is Float->putFloat(name,value)
            is Int->putInt(name,value)
            is Boolean->putBoolean(name,value)
            else -> {
                throw IllegalArgumentException("This type can't be saved into Preferences")
            }
        }.apply()
    }

}