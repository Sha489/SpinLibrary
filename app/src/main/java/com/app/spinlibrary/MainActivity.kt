package com.app.spinlibrary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import rubikstudio.library.LuckyWheelView
import rubikstudio.library.model.LuckyItem
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    var luckyWheelView: LuckyWheelView? = null
    var luckyItems: ArrayList<LuckyItem> ?= null
    var orginalBitmap: Bitmap ?= null

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val mainHandler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        luckyWheelView = findViewById(R.id.luckyWheel)

        orginalBitmap = BitmapFactory.decodeResource(
            applicationContext.getResources(),
            R.drawable.gift_white
        )

        setData()
    }

    fun setData() {
        luckyItems = ArrayList()
        luckyItems?.add(LuckyItem("1000", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_orange_light), ContextCompat.getColor(applicationContext, R.color.spin_orange), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
        luckyItems?.add(LuckyItem("3000", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_blue_light),ContextCompat.getColor(applicationContext, R.color.spin_blue), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
        luckyItems?.add(LuckyItem("50", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_orange_light),ContextCompat.getColor(applicationContext, R.color.spin_orange),ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
        luckyItems?.add(LuckyItem("100", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_blue_light), ContextCompat.getColor(applicationContext, R.color.spin_blue), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
        luckyItems?.add(LuckyItem("80", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_orange_light), ContextCompat.getColor(applicationContext, R.color.spin_orange), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
        luckyItems?.add(LuckyItem("4000", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_blue_light), ContextCompat.getColor(applicationContext, R.color.spin_blue), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
//        luckyItems?.add(LuckyItem("40", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_orange_light), ContextCompat.getColor(applicationContext, R.color.spin_orange), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))
//        luckyItems?.add(LuckyItem("40", "SAR", ContextCompat.getColor(applicationContext, R.color.spin_blue_light), ContextCompat.getColor(applicationContext, R.color.spin_blue), ContextCompat.getColor(applicationContext, android.R.color.white),false, orginalBitmap))

//        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_purple),true))
//        luckyItems?.add(LuckyItem("70", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
//        luckyItems?.add(LuckyItem("Extra Chance", ContextCompat.getColor(applicationContext, android.R.color.darker_gray), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),false))
//        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.white), ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark),true))
//        luckyItems?.add(LuckyItem(" exciting offers", ContextCompat.getColor(applicationContext, android.R.color.darker_gray)))
//        luckyItems?.add(LuckyItem("", ContextCompat.getColor(applicationContext, android.R.color.white)))

//        luckyItems?.add(LuckyItem("1000", ContextCompat.getColor(applicationContext, android.R.color.white)))
//        luckyItems?.add(LuckyItem("3000", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem("Extra Chance for you extra", ContextCompat.getColor(applicationContext, android.R.color.holo_blue_bright)))
//        luckyItems?.add(LuckyItem("50", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem("100", ContextCompat.getColor(applicationContext, android.R.color.white)))
//        luckyItems?.add(LuckyItem("80", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem("4000", ContextCompat.getColor(applicationContext, android.R.color.holo_purple)))
//        luckyItems?.add(LuckyItem("40", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem("Extra Chance", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))
//        luckyItems?.add(LuckyItem("1000", ContextCompat.getColor(applicationContext, android.R.color.holo_green_dark)))

        luckyWheelView?.setData(luckyItems)
        luckyWheelView?.setTouchEnabled(false)
        luckyWheelView?.setBorderWidth(0)
        luckyWheelView?.setBorderColor(ContextCompat.getColor(applicationContext, R.color.white))
        luckyWheelView?.setLuckyWheelBackgrouldColor(ContextCompat.getColor(applicationContext, R.color.black))
        luckyWheelView?.startLuckyWheelWithTargetIndex(6)

        luckyWheelView?.setLuckyRoundItemSelectedListener {

        }

    }
}