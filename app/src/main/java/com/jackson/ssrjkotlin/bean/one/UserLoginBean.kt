package com.jackson.ssrjkotlin.bean.one

/**
 * Created by Lenovo on 2018/1/25.
 * 第一种，层次比较简单
 * 直接定义数据类
 */
data class UserLoginBean(var userAlias: String,
                         var memberID: String,
                         var memberCode: String,
                         var userType: String,
                         var isShared: String,
                         var page: String,
                         var status: String,
                         var message: String,
                         var isHasNextPage: Boolean,
                         var isHasData: Boolean) {

}