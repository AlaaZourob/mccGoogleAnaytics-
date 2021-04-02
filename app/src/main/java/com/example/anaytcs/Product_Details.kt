package com.example.anaytcs

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_product__details.*


class Product_Details : AppCompatActivity() {

    private lateinit var mfirebaseAnalytics: FirebaseAnalytics
  //  private  var time : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product__details)


        xScreen("Product__Details")


        val timer: CountUpTimer = object : CountUpTimer(30000) {
            override fun onTick(second: Int) {
                timerView.setText(second.toString())
            }
        }
        timer.start()



//////// Second method //////////
      //  val timer = object: CountDownTimer(300 * 1000, 1000) {
           // override fun onTick(millisUntilFinished: Long) {
         //       time++
          //  }
          //  override fun onFinish() {
          //  }
      //  }
      //  timer.start()


        mfirebaseAnalytics = FirebaseAnalytics.getInstance(this)


        imageView.setOnClickListener {
            xtrack("ClickImageProduct", imageView.toString())
        }
        prductName.setOnClickListener{
            xtrack("ClickNameProduct", prductName.toString())
        }
        Core.setOnClickListener{
            xtrack("ClickCorProduct", Core.toString())
        }
        ram.setOnClickListener{
            xtrack("ClickRamProduct", ram.toString())
        }


    }

    private fun xtrack(Item: String, data: String){
        val bundle = Bundle()
        bundle.putString(Item, data)
        mfirebaseAnalytics.logEvent("Product_Details", bundle)
    }
    private fun xScreen(screenName: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Product_Details")
        mfirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        var bundle = Bundle()
        bundle.putString("Product_Details_Timer",timerView.toString())
        mfirebaseAnalytics.logEvent("Product_Details_Timer",bundle)

    }
}

