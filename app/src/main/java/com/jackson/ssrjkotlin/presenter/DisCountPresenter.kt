package com.jackson.ssrjkotlin.presenter

import com.jackson.ssrjkotlin.apiservice.MyCallBack
import com.jackson.ssrjkotlin.bean.parameter.DisCountBody
import com.jackson.ssrjkotlin.bean.two.ShopsBean
import com.jackson.ssrjkotlin.model.DisCountModel
import com.jackson.ssrjkotlin.model.IModel
import com.jackson.ssrjkotlin.view.IView
import io.reactivex.disposables.Disposable

/**
 * DisCountPresenter  2018-02-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 07
 */
class DisCountPresenter(iDisCountView: IView.IDisCountView) {

    private var mIDisCountView: IView.IDisCountView? = null
    private var mDisCountModel: IModel.IDiscountModel? = null


    init {
        mIDisCountView = iDisCountView
        mDisCountModel = DisCountModel()
    }

    fun getData(){
        var disCountBody:DisCountBody= DisCountBody()
        disCountBody.page=0
        disCountBody.city="北京"
        disCountBody.rowsPerPage=20
        disCountBody.memberID=""
        disCountBody.merchantType=""
        disCountBody.nameOrLandMark=""
        disCountBody.latitude="39.933080"
        disCountBody.longitude="116.515896"

        mDisCountModel!!.getData(disCountBody,object :MyCallBack<ShopsBean>{
            override fun onSuccess(response: ShopsBean) {
                if (response.status=="1"){
                    mIDisCountView!!.setData(response.merchantList!!)
                }
            }

            override fun onError(header: String, message: String) =Unit


            override fun onDispose(disposable: Disposable) =
                    mIDisCountView!!.closeDispose(disposable)


        })
    }


}