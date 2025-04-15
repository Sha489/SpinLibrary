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

        luckyItems?.add(LuckyItem("Earn 1000 points and win exiting awards..", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("adss", ContextCompat.getColor(applicationContext, R.color.white)))
        luckyItems?.add(LuckyItem("adss", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("adss", ContextCompat.getColor(applicationContext, R.color.white)))

        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(5)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.white))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.startLuckyWheelWithTargetIndex(0)
    }
}