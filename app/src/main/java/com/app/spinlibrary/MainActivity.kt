package com.app.spinlibrary

import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import rubikstudio.library.LuckyWheelView
import rubikstudio.library.model.LuckyItem

class MainActivity : AppCompatActivity() {

    var luckyWheelView: LuckyWheelView? = null
    var luckyItems: ArrayList<LuckyItem> ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        luckyWheelView = findViewById(R.id.luckyWheel)
        setData()
    }

    fun setData() {
        luckyItems = ArrayList<LuckyItem>()

        luckyItems?.add(LuckyItem("1000", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.white),false))
        luckyItems?.add(LuckyItem("3000", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),true))
        luckyItems?.add(LuckyItem("Extra Chance for you extra", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_blue_bright),false))
        luckyItems?.add(LuckyItem("50", ContextCompat.getColor(applicationContext, android.R.color.white),ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
        luckyItems?.add(LuckyItem("100", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.white),false))
        luckyItems?.add(LuckyItem("80", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_purple),true))
        luckyItems?.add(LuckyItem("40", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
        luckyItems?.add(LuckyItem("Extra Chance", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
        luckyItems?.add(LuckyItem("1000", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
//        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_purple),true))
//        luckyItems?.add(LuckyItem("70", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
//        luckyItems?.add(LuckyItem("Extra Chance", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
//        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),true))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        
        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(5)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.white))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        //luckyWheelView?.startLuckyWheelWithTargetIndex(2)


    }
}