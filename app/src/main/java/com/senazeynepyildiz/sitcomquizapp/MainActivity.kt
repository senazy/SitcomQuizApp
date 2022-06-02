package com.senazeynepyildiz.sitcomquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.ContentView
import androidx.appcompat.app.AlertDialog
import com.senazeynepyildiz.sitcomquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer : String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT= 10

    private val quizData = mutableListOf(
        mutableListOf("The Office", "Kelly Kapoor", "Marshall Eriksen", "Chandler Bing", "Cece Parekh"),
        mutableListOf("Unbreakable Kimmy Schmidt", "Titus Andromedon", "Amy Sosa", "Tahani Al-Jamil", "Dwight Schrute"),
        mutableListOf("Modern Family", "Gloria Pritchett", "Jackie Burkhart", "Terry Jeffords", "Andy Bernard"),
        mutableListOf("Friends", "Phoebe Buffay", "Kimmy Schmidt", "Barney Stinson", "Gina Linetti"),
        mutableListOf("New Girl", "Nick Miller", "Chidi Anagonye", "Jason Mendoza", "Glenn Sturgis"),
        mutableListOf("Brooklyn 99", "Rosa Diaz", "Ted Mosby", "Joey Tribbiani", "Michael Scott"),
        mutableListOf("How I Met Your Mother", "Lily Aldrin", "Jake Peralta", "Michael Kelso", "Charles Boyle"),
        mutableListOf("Superstore", "Cheyenne Lee", "Jay Pritchett", "Pam Beesly", "Amy Santiago"),
        mutableListOf("The Good Place", "Eleanor Shellstrop", "Rachel Green", "Winston Schmidt", "Haley Dunphy"),
        mutableListOf("That '70s Show", "Steven Hyde", "Ross Geller", "Jacqueline White", "Jessica Day")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //shuffle quiz
        quizData.shuffle()

        showNextQuiz()

    }


    fun showNextQuiz() {

        //update countLabel
        binding.countLabel.text = getString(R.string.count_label, quizCount)



        //pick one quiz set
        val quiz = quizData[0]

        //set question & rightAnswer
        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]


        //Remove "Question" from quiz
        quiz.removeAt( 0)

        //shuffle answer & choices
        quiz.shuffle()


        //set choices
        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]

        //remove this quiz from quizData
        quizData.removeAt(0)



    }

    fun checkAnswer(view: View) {

        //get pushed button
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if(btnText == rightAnswer) {
            //correct
            alertTitle = "Correct!"
            rightAnswerCount++

        } else {
            //wrong
            alertTitle = "Wrong..."
        }

        //create dialog
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("OK") {dialogInterface, i->
                checkQuizCount()
            }

            .setCancelable(false)
            .show()

    }

    fun checkQuizCount() {

        if(quizCount == QUIZ_COUNT){
            //show result

            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)


        }else{
            quizCount++
            showNextQuiz()
        }

    }

}