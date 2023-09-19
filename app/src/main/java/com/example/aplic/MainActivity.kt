package com.example.aplic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifestyle()
    }
    fun goToSwiper(){
        val intent: Intent = Intent(this, SwiperActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }



    fun lifestyle(){
        val butCalc:Button = findViewById(R.id.button)
        val butSwip:Button = findViewById(R.id.button2)
        butCalc.setOnClickListener { Toast.makeText(this, "Ви вже ТУТ", Toast.LENGTH_LONG).show() }
        butSwip.setOnClickListener { goToSwiper() }
    }
}