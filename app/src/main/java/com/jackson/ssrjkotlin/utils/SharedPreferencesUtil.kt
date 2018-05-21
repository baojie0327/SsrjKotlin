package com.jackson.ssrjkotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class SharedPreferencesUtil {

    fun putKey(key: String, value: String): Boolean {
        editor!!.putString(key, value)
        return editor!!.commit()
    }

    fun getKey(key: String): String {
        return sharedPreferences!!.getString(key, "")
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean): Boolean {
        editor!!.putBoolean(key, value)
        return editor!!.commit()
    }

    fun clear(): Boolean {
        editor = sharedPreferences!!.edit()
        editor!!.clear()
        return editor!!.commit()
    }

    companion object {
        private val SHARE_PREFER = "ssrj" //SP保存的xml文件名
        val LOGIN_STATUS = "login_sttaus" //登录状态

        private var editor: Editor? = null
        private var sharedPreferences: SharedPreferences? = null
        private var spUtil: SharedPreferencesUtil? = null

        fun getInstance(context: Context): SharedPreferencesUtil {
            if (spUtil == null) {
                spUtil = SharedPreferencesUtil()
                sharedPreferences = context.getSharedPreferences(SHARE_PREFER, Context.MODE_PRIVATE)
                editor = sharedPreferences!!.edit()
            }
            return spUtil as SharedPreferencesUtil
        }

        fun setString(context: Context, key: String, Value: String) {
            val mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
            val editor = mSharedPreferences.edit()
            editor.putString(key, Value)
            editor.commit()
        }

        //从配置文件中获取String类型的数据
        fun getString(context: Context, key: String): String {
            var value: String? = null
            val mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
            value = mSharedPreferences.getString(key, "")
            return value
        }

        //保存Boolean类型的数据到配置类型
        //key表示保存到参数
        //value表示参数值
        fun setBoolean(context: Context, key: String, value: Boolean) {
            val mSharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE)
            val editor = mSharedPreferences.edit()
            editor.putBoolean(key, value)
            editor.commit()
        }

        /**
         * 记录用户别名的SP文件
         * @param context 上下文
         * @param key 键
         * @param value 值
         */
        fun setNewSP(context: Context, key: String, value: String) {
            val sharedPreferences = context.getSharedPreferences("jPush", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.commit()
        }

        /**
         * 从SP文件中获取用户别名
         * @param context 上下文
         * @param key 键
         * @return 值
         */
        fun getAlias(context: Context, key: String): String {
            val sharedPreferences = context.getSharedPreferences("jPush", Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, "")

        }
    }

}
