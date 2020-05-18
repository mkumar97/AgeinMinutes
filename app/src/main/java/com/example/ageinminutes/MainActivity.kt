package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btndatepicker.setOnClickListener {
            view -> clickDatePicker(view)
            // Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }
    }

    private fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        {    view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "Date Picker Works; Selected $selectedYear/${selectedMonth+1}/$selectedDay", Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            selecteddate.setText(selectedDate)
            val formattedDate = SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH)
            val parsedDate = formattedDate.parse(selectedDate)
            val minutespassed = parsedDate!!.time / (1000) // in milli seconds and !! makes sure that input is not null
            val currentDate = formattedDate.parse(formattedDate.format(System.currentTimeMillis()))
            val totalminutes = currentDate!!.time / (1000)
            val minutesWasted = totalminutes - minutespassed
            selecteddateinmin.setText(minutesWasted.toString())
        }, year, month, day).show() // year, month, day which are passed decides which date to show when dailog opens, not selected
        // Toast.makeText(this, "Date Picker Works", Toast.LENGTH_LONG).show()
    }
}
