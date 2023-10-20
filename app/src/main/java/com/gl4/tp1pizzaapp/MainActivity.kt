package com.gl4.tp1pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Timer().schedule(object : TimerTask() {
//            override fun run() {
//                val intent = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(intent)
//            }
//        }, 5000)
    }

    fun navigateMain(view: View) {
        val intent = Intent(this@MainActivity, OrderActivity::class.java)
        startActivity(intent)
    }
}