package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val username = intent.getStringExtra(Constants.USER_NAME)
        tvusername.text = username

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSERS,0)

        tvscore.text = "Your Score is $correctAnswer out of $totalQuestions"

        btnfinish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


    }
}