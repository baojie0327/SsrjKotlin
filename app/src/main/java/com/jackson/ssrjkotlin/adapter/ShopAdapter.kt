package com.jackson.ssrjkotlin.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jackson.ssrjkotlin.R
import com.jackson.ssrjkotlin.bean.two.ShopsBean
import com.jackson.ssrjkotlin.utils.glide.GlideUtils

/**
 * ShopAdapter  2018-02-07
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */
/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2018 02 07
 */
class ShopAdapter(layoutResId: Int, data: List<ShopsBean.ShopDetails>?) : BaseQuickAdapter<ShopsBean.ShopDetails, BaseViewHolder>(layoutResId, data) {

    var type: String? = null

    override fun convert(helper: BaseViewHolder?, item: ShopsBean.ShopDetails?) {

        helper!!.setText(R.id.tv_item_shop_name, item!!.merchantName)
        helper!!.setText(R.id.tv_shop_landmark, "[" + item!!.landmark + "]")

        /**
         * 折扣
         */
        var disflag: String? = item!!.discountMerFlag
        when (disflag) {
            "1" -> {
                helper!!.setGone(R.id.tv_discount_content, true)
                when (item!!.discount) {
                    "10" -> helper!!.setText(R.id.tv_discount_content, "当前时段无折扣")
                    else -> helper!!.setText(R.id.tv_discount_content, item!!.discount)
                }
            }
            "0" -> {
                helper!!.setGone(R.id.tv_discount_content, true)
                when (item!!.discount) {
                    "10" -> helper!!.setText(R.id.tv_discount_content, "无折扣")
                    else -> helper!!.setText(R.id.tv_discount_content, item!!.discount + "折")
                }
                /**
                 * 已售为0的话，要隐藏掉已售和折扣右边的竖线
                 */
                var saleCount: String? = if (TextUtils.isEmpty(item!!.salesCount)) "" else item!!.salesCount
                if (!TextUtils.isEmpty(saleCount) && "0" != saleCount) {
                    helper!!.setGone(R.id.tx_Sold, true)
                } else {
                    helper!!.setGone(R.id.tx_Sold, false)
                }
            }
            else -> {
                helper!!.setGone(R.id.iv_benefits_bill, false);
                helper!!.setGone(R.id.tv_discount_content, false);
                helper!!.setGone(R.id.tx_Sold, false);
                helper!!.setText(R.id.tv_discount_content, "");
            }
        }

        //行业类型
        var industryType: String? = if (item!!.industryName == null) "" else item!!.industryName

        try {
            helper!!.setText(R.id.tv_item_shop_introduce1, industryType);
        } catch (e: Exception) {
        }

        var goodComment: String? = item!!.goodComment
        if (goodComment == null || goodComment == "0%" || goodComment == "") {
            helper!!.setVisible(R.id.tx_comment, false)
        } else {
            helper!!.setText(R.id.tx_comment, item!!.goodComment + "好评");
        }

        // 距离
        var distance: String? = item!!.distance
        var dist: Int = distance!!.toInt()
        if (dist > 1000) {
            var doubledist: Double = dist.toDouble()
            var d: String = String.format("%.1f", doubledist * 1.0 / 1000)
            helper!!.setText(R.id.tv_item_shop_distance, d + "km");
        } else {
            helper!!.setText(R.id.tv_item_shop_distance, item!!.distance + "m");
        }

        var options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.default_square_four)
                //  .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        if (item!!.headImg != null) {
            var imgUrl: String? = item!!.headImg

            GlideUtils.loadUrlImage(mContext,imgUrl,helper!!.getView<View>(R.id.iv_item_shop_head) as ImageView)
            /*GlideApp.with(mContext)
                    .load(imgUrl)
                    .apply(options)
                    .into(helper!!.getView<View>(R.id.iv_item_shop_head) as ImageView)*/

        } else {
          /*  GlideApp.with(mContext)
                    .load("assets://loading_100.png")
                    .apply(options)
                    .into(helper!!.getView<View>(R.id.iv_item_shop_head) as ImageView)*/
        }

        var couponFlag: Int = item!!.couponFlag
        when (couponFlag) {
            0 -> {
                helper!!.setGone(R.id.iv_coupons_voucher, false);
                helper!!.setGone(R.id.iv_coupons_free, false);
            }
            1 -> {
                helper!!.setGone(R.id.iv_coupons_voucher, false);
                helper!!.setGone(R.id.iv_coupons_free, false);
            }
            2 -> {
                helper!!.setGone(R.id.iv_coupons_voucher, false);
                helper!!.setGone(R.id.iv_coupons_free, false);
            }
            3 -> {
                helper!!.setGone(R.id.iv_coupons_voucher, false);
                helper!!.setGone(R.id.iv_coupons_free, false);
            }
        }

        when (type) {
            "1" -> {
                if (item!!.actID != "") {
                   /* GlideApp.with(mContext)
                            .load("assets://iv_hui_activity.png")
                            .apply(options)
                            .into(helper!!.getView<View>(R.id.iv_hui_activity) as ImageView);*/
                    helper!!.setGone(R.id.iv_hui_activity, true);
                } else {
                    helper!!.setGone(R.id.iv_hui_activity, false);
                }
            }
            else -> {
                if (item!!.actID != "") {
                   /* GlideApp.with(mContext)
                            .load("assets://iv_hui_activity.png")
                            .apply(options)
                            .into(helper!!.getView<View>(R.id.iv_hui_activity) as ImageView);*/
                    helper!!.setGone(R.id.iv_hui_activity, true);
                } else {
                    helper!!.setGone(R.id.iv_hui_activity, false);
                }
            }
        }


    }
}