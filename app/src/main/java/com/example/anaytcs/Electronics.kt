package com.example.anaytcs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Param.*
import kotlinx.android.synthetic.main.activity_electronics.*
import kotlinx.android.synthetic.main.activity_main.*

class Electronics : AppCompatActivity() {

    private lateinit var mfirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronics)

        mfirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        xScreen("Elctronics")

        val timer: CountUpTimer = object : CountUpTimer(30000) {
            override fun onTick(second: Int) {
                timerViewElctro.setText(second.toString())
            }
        }
        timer.start()


        Laptop.setOnClickListener {
            xtrack("Laptop", Laptop.toString())
            movemnts()
        }

        iphonex.setOnClickListener {
            xtrack("iphonex", iphonex.toString())
            movemnts()
        }

        smartTv.setOnClickListener {
            xtrack("smartTv", smartTv.toString())
            movemnts()
        }
    }
    private fun xtrack(Item: String, data: String) {
        val bundle = Bundle()
        bundle.putString(Item, data)
        mfirebaseAnalytics.logEvent("Electronics", bundle)
    }


    private fun xScreen (screenName :String){
        val bundle = Bundle()
        bundle.putString(SCREEN_NAME , screenName)
        bundle.putString(SCREEN_CLASS, "Electronics")
        mfirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW , bundle)
    }

     private fun  movemnts(){
    val i = Intent(this,Product_Details::class.java)
    startActivity(i)
    }
    override fun onDestroy() {
        super.onDestroy()
        var bundle = Bundle()
        bundle.putString("UserTimeAtElctronics_Activity",timerViewElctro.toString())
        mfirebaseAnalytics.logEvent("UserTimeAtElctronics_Activity",bundle)

    }
}