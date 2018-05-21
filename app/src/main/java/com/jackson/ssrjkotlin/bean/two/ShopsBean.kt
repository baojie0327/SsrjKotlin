package com.jackson.ssrjkotlin.bean.two

import com.jackson.ssrjkotlin.bean.BaseBean
import com.jackson.ssrjkotlin.bean.BasicBean

/**
 * Created by Lenovo on 2018/1/26.
 * 第二种，通过继承的方式
 */
class ShopsBean : BaseBean<ShopsBean.ShopDetails>() {

     var merchantList: List<ShopDetails>? = null

    class ShopDetails : BasicBean() {
         var merchantID: String? = null
         var merchantName: String? = null
         var merchantPhoto: String? = null
         var landmark: String? = null
         var latitude: String? = null
         var longitude: String? = null
         var distance: String? = null
         var couponFlag: Int = 0
         var isMember: Boolean = false
         var discountMerFlag: String? = null
         var discount: String? = null
         var salesCount: String? = null
         var industryName: String? = null//行业类型
         var goodComment: String? = null//好评度
         var headImg: String? = null
         var actID: String? = null// 活动id
    }
}