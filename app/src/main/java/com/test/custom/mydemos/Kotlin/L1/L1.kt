package com.test.custom.mydemos.Kotlin.L1

import com.test.custom.mydemos.R
import com.test.custom.mydemos.base.BaseActivity

class L1 : BaseActivity() {

    override fun setContentView() {
        setContentView(R.layout.activity_l1)
        var tv = findViewById(R.id.tv_kotlin)
        myKotlin()
    }



}

fun myKotlin () : String{
    return "MyKotlin"
}