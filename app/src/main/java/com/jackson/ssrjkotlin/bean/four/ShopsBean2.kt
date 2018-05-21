package com.jackson.ssrjkotlin.bean.four

/**
 * Created by Lenovo on 2018/1/26.
 * 第四种，层级调用
 */
data class ShopsBean2(
        var userAlias: String,
        var memberID: String,
        var memberCode: String,
        var userType: String,
        var isShared: String,
        var page: String,
        var status: String,
        var message: String,
        var isHasNextPage: Boolean,
        var isHasData: Boolean,
        var data: List<ShopDetails>) {


    data class ShopDetails(
            var merchantID: String,
            var merchantName: String,
            var merchantPhoto: String,
            var landmark: String,
            var latitude: String,
            var longitude: String,
            var distance: String,
            var couponFlag: Int,
            var isMember: Boolean,
            var discountMerFlag: String,
            var discount: String)
}