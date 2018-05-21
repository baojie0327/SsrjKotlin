package com.jackson.ssrjkotlin.view

import com.jackson.ssrjkotlin.bean.two.ShopsBean
import io.reactivex.disposables.Disposable

/**
 * Created by Lenovo on 2018/1/25.
 */
class IView {

    /**
     * 登录
     */
    interface ILoginView {
        fun getUserName(): String
        fun getPassWord(): String
        fun showToast(content: String)
        fun closeDispose(disposable: Disposable)
        fun showProgress()
        fun hideProgress()
        fun toOtherActivity()
    }

    /**
     * 获取附近的商户列表
     */
    interface IDisCountView {
        fun setData(dataList: List<ShopsBean.ShopDetails>)  // 设置数据
        fun closeDispose(disposable: Disposable)

    }


}