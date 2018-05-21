package com.jackson.ssrjkotlin.dagger.module

import com.jackson.ssrjkotlin.presenter.DisCountPresenter
import com.jackson.ssrjkotlin.view.IView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * DiscountModule  2018-04-12
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 04 12
 */
@Module
class DiscountModule {

    private lateinit var discountView: IView.IDisCountView

    /**
     * 空的构造方法,在presenter里使用
     */
    constructor() {

    }

    /**
     * 构造方法，含有参数，在Fragment使用
     */
    constructor(view: IView.IDisCountView) {
        this.discountView = view
    }


    /**
     * 提供DisCountPresenter实例
     */
    @Provides
    @Singleton
    fun provideDisCountPresenter(iDisCountView: IView.IDisCountView): DisCountPresenter =
            DisCountPresenter(iDisCountView)


    /**
     * 提供IView.IDisCountView实例
     */
    @Provides
    @Singleton
    fun provideIDisCountView(): IView.IDisCountView =
            discountView


}