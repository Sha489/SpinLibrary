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

        luckyItems?.add(LuckyItem(" Earn 1000 points and win", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem(" Spin wheel and win", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_blue_bright)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white),ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem(" win offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        
        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(5)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.white))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.startLuckyWheelWithTargetIndex(2)


    }
}