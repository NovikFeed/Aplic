package com.example.aplic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_view)
        var prog = 10
        var han = Handler(Looper.getMainLooper())
        val progress: ProgressBar = findViewById(R.id.progBar)
        var test = true
        progress.setProgress(prog)
//        for (i in 1..9) {
//            if (test) {
//                test = false
//                han.postDelayed(splash(), 1 * 1000) }
        val i: Intent = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()

        }
    }
