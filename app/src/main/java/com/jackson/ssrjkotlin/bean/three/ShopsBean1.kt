package com.jackson.ssrjkotlin.bean.three

/**
 * Created by Lenovo on 2018/1/26.
 * 第三种，通过 ShopsBean1<List<ShopDetails>>
 */
data class ShopsBean1<T>(var userAlias: String, var memberID: String,
                      var memberCode: String, var userType: String, var isShared: String, var page: String, var status: String, var message: String,
                      var isHasNextPage: Boolean, var isHasData: Boolean,var data:T) {
}