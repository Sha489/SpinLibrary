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

        luckyItems?.add(
            LuckyItem(
                "400 Points", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "600 Points", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "800 Points", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "Try Again", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "250 Points", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "100 Points", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "200 Points", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "Try Again", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "150 Points", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "500 Points", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "Try Again", ContextCompat.getColor(
                    applicationContext, R.color.black
                )
            )
        )
        luckyItems?.add(
            LuckyItem(
                "300 Points", ContextCompat.getColor(
                    applicationContext, R.color.spin_color
                )
            )
        )
/*        luckyItems?.add(LuckyItem(" Earn 1000 points and win", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem(" Spin wheel and win", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem(" win offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))
  */
        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(5)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.startLuckyWheelWithTargetIndex(2)


    }
}