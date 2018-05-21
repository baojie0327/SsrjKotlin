package com.jackson.ssrjkotlin.model

/**
 * IModel  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */


import com.jackson.ssrjkotlin.apiservice.MyCallBack
import com.jackson.ssrjkotlin.bean.one.UserLoginBean
import com.jackson.ssrjkotlin.bean.parameter.DisCountBody
import com.jackson.ssrjkotlin.bean.parameter.LoginBody
import com.jackson.ssrjkotlin.bean.two.ShopsBean

/**
 * 所有的ViewModel Interface
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 30
 */
class IModel {


    /**
     * 登录
     */
    interface ILoginModel {
        fun login(loginBody: LoginBody, callBack: MyCallBack<UserLoginBean>)  //登录
    }

    /**
     * 获取附近商户列表
     */
    interface IDiscountModel {
        fun getData(disCountBody: DisCountBody,callBack: MyCallBack<ShopsBean>)
    }

}

