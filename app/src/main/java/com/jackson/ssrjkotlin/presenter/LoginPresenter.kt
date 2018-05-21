package com.jackson.ssrjkotlin.presenter

import com.jackson.ssrjkotlin.apiservice.MyCallBack
import com.jackson.ssrjkotlin.bean.one.UserLoginBean
import com.jackson.ssrjkotlin.bean.parameter.LoginBody
import com.jackson.ssrjkotlin.model.LoginModel
import com.jackson.ssrjkotlin.view.IView
import io.reactivex.disposables.Disposable

/**
 * Created by Lenovo on 2018/1/25.
 */
class LoginPresenter(iLoginView: IView.ILoginView) {

    private var mILoginView: IView.ILoginView? = null
    private var mLoginModel: LoginModel? = null

    /**
     * 初始化
     */
    init {
        mILoginView = iLoginView
        mLoginModel = LoginModel()
    }

    fun login(){
        mILoginView!!.showProgress()   // ProgressBar
        //  要传的参数
        var loginBody: LoginBody = LoginBody(mILoginView!!.getUserName(), mILoginView!!.getPassWord())

        mLoginModel!!.login(loginBody,object :MyCallBack<UserLoginBean>{
            override fun onSuccess(response: UserLoginBean) {
                mILoginView!!.hideProgress()
                if (response.status == "1"){
                    mILoginView!!.toOtherActivity()  //登录成功，关闭页面
                }
                mILoginView!!.showToast(response.message)
            }

            override fun onError(header: String, message: String) {
                mILoginView!!.hideProgress()
                mILoginView!!.showToast(message)
            }

            override fun onDispose(disposable: Disposable) = mILoginView!!.closeDispose(disposable)


        })
    }


}