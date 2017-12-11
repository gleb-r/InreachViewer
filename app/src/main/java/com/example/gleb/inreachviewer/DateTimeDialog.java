package com.example.gleb.inreachviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.gleb.inreachviewer.TimeSpanDate.Preposition;

import java.util.Date;

/**
 * Created by Gleb on 03.12.2017.
 */

public class DateTimeDialog extends DialogFragment {
    CalendarView mCalendarView;

    interface OnDateSetListener {
        void OnDateSet(Date date, Preposition preposition);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Set date and time");
        View view = inflater.inflate(R.layout.date_time_dialog, container);
        mCalendarView = view.findViewById(R.id.calendar_view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
