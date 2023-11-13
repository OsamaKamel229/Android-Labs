package com.example.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReset.setOnClickListener {
            radioGroup.clearCheck()
            // Reset all the checkboxes to not checked
            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
        }

        btnSubmit.setOnClickListener {
            val currentDate = LocalDateTime.now()

            var totalQuizScore = 0 // Initialize the total Score

            if(radioGroup.checkedRadioButtonId == btnRadio2.id) {
                totalQuizScore += 50
            }

            if (checkBox2.isChecked) {
                totalQuizScore += 25
            }

            if (checkBox3.isChecked) {
                totalQuizScore += 25
            }

            val formattedDate : String = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a").format(currentDate).toString()

            //Create an Alert dialog
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Quiz Result")
            builder.setMessage("You submitted the Quiz on $formattedDate  and you have achieved $totalQuizScore%")

            builder.setIcon(android.R.drawable.ic_dialog_info)

            builder.setPositiveButton("Dismiss"){ _, _ ->
                Toast.makeText(applicationContext,
                    "Dismissed",
                    Toast.LENGTH_SHORT).show()
            }

            val alertDialog: AlertDialog = builder.create()

            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }
}
