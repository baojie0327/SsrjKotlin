package com.jackson.ssrjkotlin.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by Lenovo on 2018/1/12.
 * 通过object来声明是单例模式
 */
object Utils {

    private var toast: Toast? = null

    /**
     * 显示Toast
     */
    fun showToast(context: Context, str: String) {
        if (toast==null){
            toast=Toast.makeText(context, str, Toast.LENGTH_SHORT)
        }else{
            toast!!.setText(str)
        }
        toast!!.show()
    }

}