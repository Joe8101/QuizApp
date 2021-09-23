package com.example.quizapp

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.TintableCompoundDrawablesView
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlin.Result

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int =0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestions()

        setQuestion()

        optionone.setOnClickListener(this)
        optiontwo.setOnClickListener(this)
        optionthree.setOnClickListener(this)
        optionfour.setOnClickListener(this)
        btnsubmit.setOnClickListener(this)


    }

    private fun setQuestion() {

        val question: Question? = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            btnsubmit.text = "FINISH"
        }
        else {
            btnsubmit.text = "SUBMIT"
        }

        progressbar.progress = mCurrentPosition
        tvprogress.text = "$mCurrentPosition" + "/" + progressbar.max
        tvquestion.text = question!!.question
        ivimage.setImageResource(question.image)
        optionone.text = question.optionOne
        optiontwo.text = question.optionTwo
        optionthree.text = question.optionThree
        optionfour.text = question.optionFour

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, optionone)
        options.add(1, optiontwo)
        options.add(2, optionthree)
        options.add(3, optionfour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                    R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.optionone -> {
                selectedOptionView(optionone, 1)
            }
            R.id.optiontwo -> {
                selectedOptionView(optiontwo, 2)
            }
            R.id.optionthree -> {
                selectedOptionView(optionthree, 3)
            }
            R.id.optionfour -> {
                selectedOptionView(optionfour, 4)
            }
            R.id.btnsubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, com.example.quizapp.Result::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                            startActivity(intent)
                            finish()


                        }
                    }
                }else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnsubmit.text = "FINISH"

                    } else {
                        btnsubmit.text = "GO TO NEXT QUESTION"

                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1->{
                optionone.background = ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                optiontwo.background = ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                optionthree.background = ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                optionfour.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
                Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
                R.drawable.selected_option_border_bg)
    }


}

