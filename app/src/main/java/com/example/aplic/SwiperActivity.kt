package com.example.aplic

import android.app.Dialog
import android.bluetooth.BluetoothA2dp
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import java.io.File
import kotlin.random.Random

class SwiperActivity : AppCompatActivity() {
        var score:Int = 0
        var reversNum = 1
        var plusScore = 1
        var cof = 1
        var breakScore = 10
        lateinit var media : MediaPlayer;
    lateinit var swipe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swiper)
        media = MediaPlayer.create(this, R.raw.sound)
        media.start()
        media.setLooping(true)
        lifecycle()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveScore()
            media.stop()

    }

    override fun onPause() {
        super.onPause()
        saveScore()
        media.pause()
    }

    override fun onResume() {
        super.onResume()
        loadScore()
        media.start()
    }
    fun setNum(){
        reversNum +=1
        cof *= reversNum
        breakScore *=  cof
        plusScore += reversNum

    }
    fun saveScore(){
        val sPref : SharedPreferences = getPreferences(MODE_PRIVATE)
        val sPrefEditor = sPref.edit()
            sPrefEditor.putInt("saved_score", score)
            sPrefEditor.commit()
    }
    fun loadScore() : Int{
        val sPref : SharedPreferences = getPreferences(MODE_PRIVATE)
        val loadSc : Int = sPref.getInt("saved_score", 0)
        return loadSc;
    }

    fun goToCalc() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }
    fun dialogView(){
        val dial = Dialog(this)
        dial.setContentView(R.layout.dialog)
        dial.show()
        val butClose : Button = dial.findViewById(R.id.closeButton)
        butClose.setOnClickListener {
            dial.dismiss()
        }
    }
    fun getRandomColor(): Int {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        return Color.rgb(red, green, blue)}

    fun tryScore() {
        val revers: ImageButton = findViewById(R.id.imageButton)
        if ((score == breakScore) && (!revers.isVisible)) {
            revers.visibility = View.VISIBLE
            dialogView()
        } else {
            return
        }
    }
    fun tryReversScore(){
        val revers : ImageButton = findViewById(R.id.imageButton)
        if(revers.isVisible){
            revers.setOnClickListener {
                score = 0
                setNum()
                saveScore()
                loadScore()
                swipe.setText(score.toString())
                revers.visibility = View.INVISIBLE
            }
        }
        else{return}
    }

    fun lifecycle() {
        val butCalc: Button = findViewById(R.id.button3)
        val butSwip: Button = findViewById(R.id.button4)
        swipe = findViewById(R.id.button6)
        val space : ConstraintLayout = findViewById(R.id.layout)
        val but :ImageButton = findViewById(R.id.imageButton)
        score = loadScore()
        swipe.setText(loadScore().toString())
        butCalc.setOnClickListener { goToCalc() }
        butSwip.setOnClickListener { Toast.makeText(this, "Ви вже ТУТ!", Toast.LENGTH_LONG).show() }
        swipe.setOnClickListener {
            tryScore()
            tryReversScore()
            space.setBackgroundColor(getRandomColor())
            score += plusScore
                swipe.setText(score.toString())

        }
    }
}





