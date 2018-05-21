package com.jackson.ssrjkotlin.apiservice

import com.jackson.ssrjkotlin.bean.one.UserLoginBean
import com.jackson.ssrjkotlin.bean.parameter.DisCountBody
import com.jackson.ssrjkotlin.bean.parameter.LoginBody
import com.jackson.ssrjkotlin.bean.two.ShopsBean
import com.jackson.ssrjkotlin.utils.Constant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * JsNetworkService  2018-01-29
 * Copyright (c) 2018 KL Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 01 29
 */
class JsNetworkService private constructor(retrofitClient: RetrofitClient) {

     var mIJsNetworkService: IJsNetworkService

    init {
        mIJsNetworkService = retrofitClient.create(JsNetworkService.IJsNetworkService::class.java)
    }

    companion object {
        val instance = JsNetworkService(RetrofitClient(Constant.baseUrl))
    }


    interface IJsNetworkService {

        /**
         * 登录
         */
        @POST("member/login.do")
        fun getLogin(@Body loginBody: LoginBody):Observable<UserLoginBean>

        /**
         * 获取惠买单商户列表
         * @param disCountBody
         * @return
         */
        @POST("member/queryDisCountMerchant.do")
        fun getDisCountData(@Body disCountBody: DisCountBody): Observable<ShopsBean>


    }

}