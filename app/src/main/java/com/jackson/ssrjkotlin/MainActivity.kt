package com.jackson.ssrjkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.jackson.ssrjkotlin.utils.KSPUtil
import com.jackson.ssrjkotlin.utils.SPUtil
import com.jackson.ssrjkotlin.utils.SharedPreferencesUtil
import com.jackson.ssrjkotlin.view.activity.LoginActivity
import com.jackson.ssrjkotlin.view.fragment.DisCountFragment
import com.jackson.ssrjkotlin.view.fragment.MineFragment
import com.jackson.ssrjkotlin.view.fragment.NearbyFragment
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationBar.OnTabSelectedListener {


    private var mflContent: FrameLayout? = null    //布局
    private var mBottomNavigationBar: BottomNavigationBar? = null  //BottomBar控件
    private var mTextBadgeItem: TextBadgeItem? = null  //  item提示文字
    private var mShapeBadgeItem: ShapeBadgeItem? = null //item提示小圆点
    private var lastSelectedPosition: Int = 0
    // 三个Fragment
    private var mNearbyFragment: NearbyFragment? = null
    private var mDisCountFragment: DisCountFragment? = null
    private var mMineFragment: MineFragment? = null

    // Fragment管理器，和执行器
    private var mManager: FragmentManager? = null
    private var mTransaction: FragmentTransaction? = null

    private var messCout: Int = 0  //消息数
    private var mSPUtil: SPUtil? = null   // SP

    private var isLogin: Boolean = false

    private var userId: String by KSPUtil(this, "userId", "")

    var from: String? = null

    /**
     * 扩展Context
     */
    private fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
            Toast.makeText(this@MainActivity, message, duration).show()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView();
        setDefaultFragment()
        testKSP()
        from = intent.getStringExtra("from")
        if (from != null) {
           toast(from!!)
        }
    }

    /**
     * Kotlin委托SharePerfence测试
     */
    private fun testKSP() = if (userId.isEmpty()) {
        Log.d("hbj--", "userId is empty")
     //   toast("userId is empty")
        userId = "default"
    } else {
        Log.d("hbj--", "userId is $userId")
      //  toast("userId is $userId")
    }

    /**
     * 初始化界面控件
     */
    private fun initView() {
        //初始化控件
        mflContent = find(R.id.ll_content)   // Anko find
        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar) as BottomNavigationBar

        // TODO 设置模式
        mBottomNavigationBar!!.setMode(BottomNavigationBar.MODE_FIXED)
        // TODO 设置背景色样式
        mBottomNavigationBar!!.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        mBottomNavigationBar!!.setBarBackgroundColor(R.color.background_gray_color)
        // 设置item的提示文字
        mTextBadgeItem = TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.main_color)
                .setText("5")
                .setTextColorResource(R.color.white)
                .setBorderColorResource(R.color.colorPrimaryDark)  //外边界颜色
                .setHideOnSelect(false)

        // 设置item上提示小圆点
        mShapeBadgeItem = ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_OVAL)
                .setShapeColor(R.color.main_color)
                .setShapeColorResource(R.color.main_color)
                .setSizeInDp(this, 10, 10)
                .setEdgeMarginInDp(this, 2)
                //                .setSizeInPixels(30,30)
                //                .setEdgeMarginInPixels(-1)
                .setGravity(Gravity.TOP or Gravity.END)
                .setHideOnSelect(false)

        mBottomNavigationBar!!.addItem(BottomNavigationItem(R.mipmap.tab_home_pressed, "首页").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_home_normal).setInActiveColorResource(R.color.icon_color))
                .addItem(BottomNavigationItem(R.mipmap.tab_benefits_check, "资讯").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_benefits_check_no).setInActiveColorResource(R.color.icon_color).setBadgeItem(mShapeBadgeItem))
                .addItem(BottomNavigationItem(R.mipmap.tab_mine, "我的").setActiveColorResource(R.color.main_color).setInactiveIconResource(R.mipmap.tab_mine_off).setInActiveColorResource(R.color.icon_color).setBadgeItem(mTextBadgeItem))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise()

        //item切换监听
        mBottomNavigationBar!!.setTabSelectedListener(this)

    }

    /**
     * 设置默认的Fragment
     */
    private fun setDefaultFragment() {
        mNearbyFragment = NearbyFragment.newInstance()
        mManager = supportFragmentManager
        mTransaction = mManager!!.beginTransaction()
        mTransaction!!.add(R.id.ll_content, mNearbyFragment)
        mTransaction!!.commit()
    }

    override fun onTabSelected(position: Int) {
        lastSelectedPosition = position
        //开启事务
        mTransaction = mManager!!.beginTransaction()
        hideFragment(mTransaction)
        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        when (position) {
            0 -> if (mNearbyFragment == null) {
                mNearbyFragment = NearbyFragment.newInstance()
                mTransaction!!.add(R.id.ll_content, mNearbyFragment)
            } else {
                mTransaction!!.show(mNearbyFragment)
            }
            1 -> if (mDisCountFragment == null) {
                mDisCountFragment = DisCountFragment.newInstance()
                mTransaction!!.add(R.id.ll_content, mDisCountFragment)
            } else {
                mTransaction!!.show(mDisCountFragment)
            }
            2 -> {
                isLogin = SPUtil.getBoolean(this@MainActivity, SharedPreferencesUtil.LOGIN_STATUS, false)
                if (!isLogin) {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    if (mMineFragment == null) {
                        mMineFragment = MineFragment.newInstance()
                        mTransaction!!.add(R.id.ll_content, mMineFragment)
                    } else {
                        mTransaction!!.show(mMineFragment)
                    }
                }
            }

        }
        // 事务提交
        mTransaction!!.commit()
    }


    /**
     * 控件监听
     */
    override fun onClick(p0: View?) {
        when (p0!!.id) {


        }
    }

    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private fun hideFragment(transaction: FragmentTransaction?) {
        if (mNearbyFragment != null) {
            transaction!!.hide(mNearbyFragment)
        }
        if (mDisCountFragment != null) {
            transaction!!.hide(mDisCountFragment)
        }
        if (mMineFragment != null) {
            transaction!!.hide(mMineFragment)
        }
    }


    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }


}
