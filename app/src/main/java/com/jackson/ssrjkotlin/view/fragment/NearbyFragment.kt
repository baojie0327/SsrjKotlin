package com.jackson.ssrjkotlin.view.fragment

/**
 * NearbyFragment  2017-09-12
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jackson.ssrjkotlin.R


/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 09 12
 */
class NearbyFragment : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(R.layout.fragment_nearby_layout, null)
        }
        return rootView
    }

    /**
     * 伴生对象，提供Fragment的实例
     */
    companion object {
        fun newInstance(): NearbyFragment {
            val fragment = NearbyFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

}

