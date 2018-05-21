package com.jackson.ssrjkotlin.bean

import java.io.Serializable

/**
 * Created by Lenovo on 2018/1/25.
 */
open class BaseBean<T> : Serializable {
    var page: String = ""
    var status: String = ""
    var message: String = ""
    var isHasNextPage: Boolean = false
    var isHasData: Boolean = false//因为有的接口有下一页返回的字段不一样
    var data: List<T> = ArrayList<T>()
}