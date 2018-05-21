package com.jackson.ssrjkotlin.dagger.component

import com.jackson.ssrjkotlin.dagger.module.DiscountModule
import com.jackson.ssrjkotlin.view.fragment.DisCountFragment
import dagger.Component
import javax.inject.Singleton

/**
 * DiscountComponent  2018-04-12
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 04 12
 */
@Singleton
@Component(modules = arrayOf(DiscountModule::class))
interface DiscountComponent {
    fun inject(disCountFragment: DisCountFragment)
}