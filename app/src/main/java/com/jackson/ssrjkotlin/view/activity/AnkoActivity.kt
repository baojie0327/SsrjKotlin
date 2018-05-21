package com.jackson.ssrjkotlin.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jackson.ssrjkotlin.MainActivity
import com.jackson.ssrjkotlin.R
import kotlinx.android.synthetic.main.activity_anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko)
        hello.text = "hello world" // 实际上这个hello是xml里的id
        hello.onClick {
        //    startActivity(intentFor<MainActivity>("from" to "anko"))
            startActivity<MainActivity>("from" to "anko")
        }
    }
}
