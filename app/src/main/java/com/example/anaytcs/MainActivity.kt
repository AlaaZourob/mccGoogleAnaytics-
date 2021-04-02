package com.example.anaytcs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product__details.*


class MainActivity : AppCompatActivity() {

    private lateinit var mfirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mfirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        xScreen("MainActivityScreen")

        val timer: CountUpTimer = object : CountUpTimer(30000) {
            override fun onTick(second: Int) {
                timerViewMain.setText(second.toString())
            }
        }
        timer.start()





        Food.setOnClickListener {
            val i = Intent(applicationContext, Food::class.java)
            startActivity(i)
            xtrack("Food",Food.toString())


        }

        Elctronics.setOnClickListener {
            val i  =  Intent(applicationContext, Elctronics::class.java)
            startActivity(i)
            xtrack("ectronic",Elctronics.toString())


        }

        Clothes.setOnClickListener {
            val i  = Intent(applicationContext,Clothes::class.java)
            startActivity(i)
            xtrack("Clothes",Clothes.toString())



        }

    }

    private fun xtrack (Item: String, data: String){
        val bundle = Bundle()
        bundle.putString(Item , data)
        mfirebaseAnalytics.logEvent("MainActivity", bundle)
    }
    private fun xScreen (screenName :String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME , screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        mfirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW , bundle)
    }
    override fun onDestroy() {
        super.onDestroy()
        var bundle = Bundle()
        bundle.putString("UserTimeAtMain_Activity",timerViewMain.toString())
        mfirebaseAnalytics.logEvent("UserTimeAtMain_Activity",bundle)

    }
}