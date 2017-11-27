package com.example.gleb.inreachviewer

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gleb on 21.11.2017.
 */
data class TimeSpanDate(val timePreposition: Preposition, var date: Date) {

    enum class Preposition {
        FROM,
        TO;
    }

    // TODO переобразование соотв локализации
    fun getDateStr(): String {
        val dateParser = SimpleDateFormat("dd.MM.yyyy hh:mm")
        return dateParser.format(date)
    }

    fun setDateYMD (year:Int, month:Int, day:Int) {
        val calendar = Calendar.getInstance()
        Log.i("TAG", "Before $date")
        calendar.time = date
        calendar.set(year,month,day)
        date = calendar.time
        Log.i("TAG", "After $date")

    }
}
