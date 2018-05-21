package com.jackson.ssrjkotlin.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.jackson.ssrjkotlin.R

/**
 * CommonMethod  2018-02-05
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 05
 */
class CommonMethod {

    companion object {

        fun showToast(context: Context, text: String, isLongLength: Boolean) {
            var toast: Toast? = null
            var length = if (isLongLength) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            if (toast == null) {
                toast = Toast.makeText(context, text, length)
            } else {
                toast.setText(text)
                toast.duration = length
            }

            var view: View = toast!!.view
            view.setBackgroundResource(R.drawable.shape_toast_background)
            toast.view = view
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()

        }
    }
}