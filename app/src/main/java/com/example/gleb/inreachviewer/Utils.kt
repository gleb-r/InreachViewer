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
    val dateParser = SimpleDateFormat("dd.MM.yyyy HH:mm",Locale.US)
    return dateParser.format(this)

//    return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this)
}

