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
   /*     luckyItems?.add(LuckyItem(R.drawable.seven,ContextCompat.getColor(applicationContext, R.color.yellow)))
        luckyItems?.add(LuckyItem( R.drawable.apple,ContextCompat.getColor(applicationContext, R.color.orange)))
        luckyItems?.add(LuckyItem( R.drawable.bar,ContextCompat.getColor(applicationContext, R.color.green)))
        luckyItems?.add(LuckyItem( R.drawable.apple,ContextCompat.getColor(applicationContext, R.color.yellow)))
        luckyItems?.add(LuckyItem( R.drawable.seven,ContextCompat.getColor(applicationContext, R.color.orange)))
        luckyItems?.add(LuckyItem( R.drawable.lemon,ContextCompat.getColor(applicationContext, R.color.green)))
        luckyItems?.add(LuckyItem( R.drawable.seven,ContextCompat.getColor(applicationContext, R.color.orange)))
        luckyItems?.add(LuckyItem( R.drawable.lemon,ContextCompat.getColor(applicationContext, R.color.green)))

    */


        luckyItems!!.add(
            LuckyItem(
                "Play Station",
                ContextCompat.getColor(applicationContext, R.color.green_light),
                ContextCompat.getColor(applicationContext, R.color.green_dark)
            )
        )
        luckyItems!!.add(
            LuckyItem(
                "65% Off on McAfe",
                ContextCompat.getColor(applicationContext, R.color.grey_light),
                ContextCompat.getColor(applicationContext, R.color.grey_dark)
            )
        )
        luckyItems!!.add(
            LuckyItem(
                "Try & Again",
                ContextCompat.getColor(applicationContext, R.color.green_light),
                ContextCompat.getColor(applicationContext, R.color.green_dark)
            )
        )
        luckyItems!!.add(
            LuckyItem(
                "خصم 15% على أمازون على",
                ContextCompat.getColor(applicationContext, R.color.grey_light),
                ContextCompat.getColor(applicationContext, R.color.grey_dark)
            )
        )
        luckyItems!!.add(
            LuckyItem(
                "Try Again",
                ContextCompat.getColor(applicationContext, R.color.green_light),
                ContextCompat.getColor(applicationContext, R.color.green_dark)
            )
        )
        luckyItems!!.add(
            LuckyItem(
                "15% Off on Amazon",
                ContextCompat.getColor(applicationContext, R.color.grey_light),
                ContextCompat.getColor(applicationContext, R.color.grey_dark)
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
        luckyWheelView?.setBorderWidth(35)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        //luckyWheelView?.startLuckyWheelWithTargetIndex(5)


    }
}