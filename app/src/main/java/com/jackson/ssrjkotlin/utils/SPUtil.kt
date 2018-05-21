package com.jackson.ssrjkotlin.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Lenovo on 2018/1/19.
 * object+名称 声明一个单例
 */
object SPUtil {
    private val SHARE_PREFER = "ssrj" //SP保存的xml文件名
    val LOGIN_STATUS = "login_status" //登录状态
    private var editor: SharedPreferences.Editor? = null
    private var sharedPreferences: SharedPreferences? = null

    //保存String类型
    fun setString(context: Context, key: String, value: String) {
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        val editor = mSharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    //从配置文件中获取String类型的数据
    fun getString(context: Context, key: String): String {
        var value: String? = null
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        value = mSharedPreferences.getString(key, "")
        return value
    }

    /**
     * 保存int类型
     */
    fun setInt(context: Context, key: String, value: Int) {
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        val editor = mSharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    //从配置文件中获取int类型的数据
    fun getInt(context: Context, key: String): Int {
        var value: Int? = null
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        value = mSharedPreferences.getInt(key, -1)
        return value
    }


    /**
     * 保存Boolean类型
     */
    fun setBoolean(context: Context, key: String, value: Boolean) {
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        val editor = mSharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    //从配置文件中获取Boolean类型的数据
    fun getBoolean(context: Context, key: String,defaultValue: Boolean): Boolean {
        var value: Boolean? = null
        val mSharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
        value = mSharedPreferences.getBoolean(key, defaultValue)
        return value
    }


}