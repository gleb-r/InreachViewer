@file:JvmName("DateUtils")
package com.example.gleb.inreachviewer

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gleb on 23.11.2017.
 */

enum class DatePreposition {
    FROM,
    TO;
}



fun Date.getDateStr(): String {
    //TODO(Локализация приведениЯ даты и времени)
    val dateParser = SimpleDateFormat("dd.MM.yyyy hh:mm")
    return dateParser.format(this)
}

//fun setDateYMD(year: Int, month: Int, day: Int):Date {
//    val calendar = Calendar.getInstance()
//    calendar.time = this
//    calendar.set(year, month, day)
//    return calendar.time
//}

