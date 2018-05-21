package com.jackson.ssrjkotlin.model

import com.jackson.ssrjkotlin.apiservice.JsNetworkService
import com.jackson.ssrjkotlin.apiservice.MyCallBack
import com.jackson.ssrjkotlin.bean.parameter.DisCountBody
import com.jackson.ssrjkotlin.bean.two.ShopsBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * DisCountModel  2018-02-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 07
 */
class DisCountModel : IModel.IDiscountModel {

    override fun getData(disCountBody: DisCountBody, callBack: MyCallBack<ShopsBean>) =
            JsNetworkService.instance.mIJsNetworkService
                    .getDisCountData(disCountBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ShopsBean> {
                        override fun onComplete() = Unit

                        override fun onNext(t: ShopsBean) =
                                callBack.onSuccess(t)

                        override fun onError(e: Throwable) =
                                callBack.onError("Server Error", "服务器异常，请稍后再试")


                        override fun onSubscribe(d: Disposable) {
                            var disPosable: Disposable = d
                            callBack.onDispose(disPosable)
                        }

                    })

}