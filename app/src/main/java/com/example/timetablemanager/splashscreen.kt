package com.example.timetablemanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.timetablemanager.firebase.login

class splashscreen :AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        val backendTag:ImageView=findViewById(R.id.splashScreenImageView)
        val sideAnimation=AnimationUtils.loadAnimation(this,R.anim.slide)
        backendTag.startAnimation(sideAnimation)

        Handler().postDelayed({
                    startActivity(Intent(this,login::class.java))
            finish()

        },3000)


    }
}