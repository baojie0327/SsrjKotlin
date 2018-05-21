package com.jackson.ssrjkotlin.view.fragment

/**
 * DisCountFragment  2017-09-14
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.jackson.ssrjkotlin.R
import com.jackson.ssrjkotlin.adapter.ShopAdapter
import com.jackson.ssrjkotlin.bean.two.ShopsBean
import com.jackson.ssrjkotlin.dagger.component.DaggerDiscountComponent
import com.jackson.ssrjkotlin.dagger.module.DiscountModule
import com.jackson.ssrjkotlin.presenter.DisCountPresenter
import com.jackson.ssrjkotlin.view.IView
import io.reactivex.disposables.Disposable
import javax.inject.Inject


/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 09 14
 */
class DisCountFragment : Fragment(), IView.IDisCountView {


    private var rootView: View? = null
    private var llBack: LinearLayout? = null
    private var tvTitle: TextView? = null
    private var mRecyclerView: RecyclerView? = null
    private var mShopAdapter: ShopAdapter? = null

    @Inject
    lateinit var mDisCountPresenter: DisCountPresenter
    //  private var mDisCountPresenter: DisCountPresenter = DisCountPresenter(this)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_discount_layout, null)
            inject()
            initView()
            mDisCountPresenter.getData()
        }
        return rootView
    }

    /**
     * 注入操作
     */
    private fun inject() = DaggerDiscountComponent.builder()
            .discountModule(DiscountModule(this))
            .build()
            .inject(this)

    /**
     * 初始化ui
     */
    private fun initView() {
        // 隐藏返回键
        llBack = rootView!!.findViewById(R.id.ll_back)
        llBack!!.visibility = View.INVISIBLE
        // 设置title
        tvTitle = rootView!!.findViewById(R.id.tv_head_title)
        tvTitle!!.text = "资讯"
        // 设置RecyclerView
        mRecyclerView = rootView!!.findViewById(R.id.recyclerView)
        var layoutManger: LinearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = layoutManger
    }

    override fun setData(dataList: List<ShopsBean.ShopDetails>) {
        mShopAdapter = ShopAdapter(R.layout.item_shop2, dataList)
        mShopAdapter!!.type = "0"
        mRecyclerView!!.adapter = mShopAdapter
    }

    override fun closeDispose(disposable: Disposable) = Unit


    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): DisCountFragment {
            val fragment = DisCountFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

}

