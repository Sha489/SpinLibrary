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

        luckyItems?.add(LuckyItem(R.drawable.reward_icon,ContextCompat.getColor(applicationContext, R.color.light_orange), ContextCompat.getColor(applicationContext, R.color.dark_orange)))
        luckyItems?.add(LuckyItem("1", ContextCompat.getColor(applicationContext, R.color.purple)))
        luckyItems?.add(LuckyItem(R.drawable.reward_icon, ContextCompat.getColor(applicationContext, R.color.light_orange), ContextCompat.getColor(applicationContext, R.color.dark_orange)))
        luckyItems?.add(LuckyItem("3",ContextCompat.getColor(applicationContext, R.color.purple)))
        luckyItems?.add(LuckyItem(R.drawable.reward_icon,ContextCompat.getColor(applicationContext, R.color.light_orange), ContextCompat.getColor(applicationContext, R.color.dark_orange)))
        luckyItems?.add(LuckyItem("5", ContextCompat.getColor(applicationContext, R.color.purple)))
        luckyItems?.add(LuckyItem(R.drawable.reward_icon,ContextCompat.getColor(applicationContext, R.color.light_orange), ContextCompat.getColor(applicationContext, R.color.dark_orange)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, R.color.purple)))
        luckyItems?.add(LuckyItem(R.drawable.reward_icon,ContextCompat.getColor(applicationContext, R.color.light_orange), ContextCompat.getColor(applicationContext, R.color.dark_orange)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, R.color.purple)))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        
        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(0)
        luckyWheelView?.setRound(10)
        //luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.white))

        luckyWheelView?.startLuckyWheelWithTargetIndex(7)


    }
}