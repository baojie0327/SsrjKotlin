package com.jackson.ssrjkotlin.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.jackson.ssrjkotlin.R
import com.jackson.ssrjkotlin.presenter.LoginPresenter
import com.jackson.ssrjkotlin.utils.CommonMethod
import com.jackson.ssrjkotlin.view.IView
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.find

class LoginActivity : AppCompatActivity(), View.OnClickListener, IView.ILoginView {

    private var mLLBack: LinearLayout? = null  //返回
    private var tvHeadTitle: TextView? = null  //title
    private var mTlUsername: TextInputLayout? = null
    private var mTlPassword: TextInputLayout? = null
    private var mBtnLogin: Button? = null
    private var mProgressbar: ProgressBar? = null
    private var mDisposable: Disposable? = null  // Activity销毁时，关闭Disposable
    private var mBtnAnko: Button? = null
    private var mLoginPresenter: LoginPresenter = LoginPresenter(this@LoginActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose();
        }

    }

    /**
     * 初始化控件
     */
    private fun initView() {
        mLLBack = findViewById(R.id.ll_back) as LinearLayout
        mLLBack!!.setOnClickListener(this)
        tvHeadTitle = findViewById(R.id.tv_head_title) as TextView
        mTlUsername = findViewById(R.id.tl_username) as TextInputLayout
        mTlPassword = findViewById(R.id.tl_password) as TextInputLayout
        mBtnLogin = findViewById(R.id.btn_login) as Button
        mBtnLogin!!.setOnClickListener(this@LoginActivity)
        mProgressbar = findViewById(R.id.progressbar) as ProgressBar
        mBtnAnko=find(R.id.btn_anko)  // 跳转到Ako测试
        mBtnAnko!!.setOnClickListener(this)
        tvHeadTitle!!.text = "登录"
        //输入账号监听
        mTlUsername!!.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var number: String = p0.toString().trim() // 输入账号
                var inputPasswd: String = mTlPassword!!.editText!!.text.toString()

                if (number.isNotEmpty() && inputPasswd.length >= 6) {
                    mBtnLogin!!.setBackgroundResource(R.drawable.shape_rectangle_main_color)
                } else {
                    mBtnLogin!!.setBackgroundResource(R.drawable.shape_button_gray)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })

        // 输入密码监听
        mTlPassword!!.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                var number: String = mTlUsername!!.editText!!.text.toString()
                var inputPasswd: String = p0.toString().trim()
                if (number.isNotEmpty() && inputPasswd.length >= 6) {
                    mBtnLogin!!.setBackgroundResource(R.drawable.shape_rectangle_main_color)
                } else {
                    mBtnLogin!!.setBackgroundResource(R.drawable.shape_button_gray)
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        })


    }


    /**
     * 控件的监听
     */
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.ll_back -> this@LoginActivity.finish()
            R.id.btn_login -> login()
            R.id.btn_anko->{startActivity(Intent(this@LoginActivity,AnkoActivity::class.java))}
        }
    }

    private fun login() =
            when {
                TextUtils.isEmpty(getUserName()) -> {
                    mTlUsername!!.isErrorEnabled = true
                    mTlUsername!!.error = "账号不能为空"
                }
                TextUtils.isEmpty(getPassWord()) -> {
                    mTlPassword!!.isErrorEnabled = true
                    mTlPassword!!.error = "密码不能为空"
                }
                else -> mLoginPresenter.login()
            }


    /**
     * 获取输入的账号
     */
    override fun getUserName(): String = mTlUsername!!.editText!!.text.toString().trim()


    override fun getPassWord(): String = mTlPassword!!.editText!!.text.toString().trim()


    override fun showToast(content: String) {
        if (!TextUtils.isEmpty(content)) {
            CommonMethod.showToast(this@LoginActivity, content, false)
        }
    }

    override fun closeDispose(disposable: Disposable) {
        mDisposable = disposable
    }

    override fun showProgress() {
        mProgressbar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mProgressbar!!.visibility = View.GONE
    }

    override fun toOtherActivity() = this@LoginActivity.finish()


}
