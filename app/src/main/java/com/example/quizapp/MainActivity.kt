package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnstrt
import kotlinx.android.synthetic.main.activity_result.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        btnstrt.setOnClickListener {
            if (etname.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter your Name", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etname.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}

