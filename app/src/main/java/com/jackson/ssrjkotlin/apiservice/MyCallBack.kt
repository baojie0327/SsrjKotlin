package com.jackson.ssrjkotlin.apiservice

import io.reactivex.disposables.Disposable

/**
 * MyCallBack  2018-01-30
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 01 30
 */
interface MyCallBack<in T> {
    fun onSuccess(response: T)          //成功回调
    fun onError(header: String, message: String)    //失败回调
    fun onDispose(disposable: Disposable)    // 切断上有发送事件
}