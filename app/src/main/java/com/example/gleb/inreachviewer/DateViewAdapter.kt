package com.example.gleb.inreachviewer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.date_item.view.*
import java.util.*

/**
 * Created by Gleb on 21.11.2017.
 */
class DateViewAdapter(
        context: Context,
        private val datesList: Map<DatePreposition, Date>): BaseAdapter() {

    private val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: inflater.inflate(R.layout.date_item, p2, false)
        val keyPreposition = DatePreposition.values()[p0]
        val date = datesList[keyPreposition]
        if (date != null) {
            view.textView.text = keyPreposition.name
            view.textView2.text = date.getDateStr()
        }
        return view
    }


    override fun getItem(p0: Int): Any =
            datesList[DatePreposition.values()[p0]]?:
                    throw ArrayIndexOutOfBoundsException("no such key in Map")

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = datesList.size

}