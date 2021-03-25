package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById(R.id.btnDatePicker) as Button
        val textOfChosenDate = findViewById(R.id.textViewSelectDate) as TextView
        val textOfDateInMinutes = findViewById(R.id.textViewSelectDateInMinutes) as TextView

        button.setOnClickListener{view ->
            clickDatePicker(view,textOfChosenDate,textOfDateInMinutes)
        }



    }


    fun clickDatePicker(view: View,text : TextView,text2 : TextView){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, yearChosen, monthChosen, dayOfMonthChosen ->
            Toast.makeText(this, "The chosen year is $yearChosen, the month is ${monthChosen+1} and the day is $dayOfMonthChosen",Toast.LENGTH_LONG).show()
            val selectedDate  = "$dayOfMonthChosen/${monthChosen+1}/$yearChosen"
           // text.setText(selectedDate)
            val formatForWrite = SimpleDateFormat("EEE/MMM/yyyy")
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY)
            val theDate = sdf.parse(selectedDate)
            text.setText(formatForWrite.format(theDate))

            val selectedDateToMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            // Current date in to minutes.
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
            text2.setText(differenceInMinutes.toString())

        }, year, month,day).show()
    }


}