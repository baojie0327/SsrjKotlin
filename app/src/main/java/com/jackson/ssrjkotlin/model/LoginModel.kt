package com.jackson.ssrjkotlin.model

import com.jackson.ssrjkotlin.apiservice.JsNetworkService
import com.jackson.ssrjkotlin.apiservice.MyCallBack
import com.jackson.ssrjkotlin.bean.one.UserLoginBean
import com.jackson.ssrjkotlin.bean.parameter.LoginBody
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * LoginModel  2018-01-30
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 01 30
 */
class LoginModel : IModel.ILoginModel {

    override fun login(loginBody: LoginBody, callBack: MyCallBack<UserLoginBean>) =
        JsNetworkService.instance.mIJsNetworkService
                .getLogin(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<UserLoginBean> {
                    override fun onError(e: Throwable) =
                            callBack.onError("Server Error", "服务器异常，请稍后再试")

                    override fun onNext(t: UserLoginBean) =
                            callBack.onSuccess(t)


                    override fun onSubscribe(d: Disposable) {
                        var mDisposable: Disposable = d
                        callBack.onDispose(mDisposable)
                    }

                    override fun onComplete() = Unit

                })

}