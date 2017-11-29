package com.example.gleb.inreachviewer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.gleb.inreachviewer.InreachMapFragment.MapType;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gleb.inreachviewer.DatePreposition.FROM;
import static com.example.gleb.inreachviewer.DatePreposition.TO;
import static com.example.gleb.inreachviewer.DatePreposition.values;


public class MainActivity
        extends FragmentActivity
        implements Data.DataCallBack, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener

{
    private static final String TAG = MainActivity.class.getName();
    InreachMapFragment mInreachMapFragment;
    ConstraintLayout mConstraintLayout;
    BottomSheetBehavior mBottomSheetBehavior;
    FloatingActionButton mFabSettings;
    Data mData;
    ListView lvDates;
    Map<DatePreposition, Date> dates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDateListView();
        mConstraintLayout = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mConstraintLayout);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mFabSettings = findViewById(R.id.fab_settings);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mFabSettings.animate().scaleX(1).scaleY(1).setDuration(100).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        mFabSettings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mFabSettings.animate().scaleX(0).scaleY(0).start();
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentMap = fragmentManager.findFragmentById(R.id.map_fragment_container);
        if (fragmentMap == null) {
            fragmentMap = InreachMapFragment.newInstance();
            mInreachMapFragment = (InreachMapFragment) fragmentMap;
            fragmentManager
                    .beginTransaction()
                    .add(R.id.map_fragment_container, fragmentMap)
                    .commit();
        }
        mData = Data.getInstance(this);
    }

    void initDateListView() {
        lvDates = findViewById(R.id.lv_dates);
        dates = new HashMap<>();
        dates.put(FROM, Calendar.getInstance().getTime());
        dates.put(TO, Calendar.getInstance().getTime());
        DateViewAdapter adapter = new DateViewAdapter(this, dates);
        lvDates.setAdapter(adapter);
        lvDates.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatePreposition key = values()[i];
                Date date = dates.get(key);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String dialogTag = key.name();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance
                        (MainActivity.this,
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        );
                datePickerDialog.setTitle(dialogTag);
                datePickerDialog.show(getFragmentManager(), dialogTag);
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dialogTag = view.getTag();
        DatePreposition key = DatePreposition.valueOf(dialogTag);
        // редактируем старую дату чтобы сохранить значение часов и минут
        Date oldDate = dates.get(key);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.set(year, monthOfYear, dayOfMonth);
        dates.put(key, calendar.getTime());
        //TODO сделать обновления дат и времени по закрытию фрагмета с выбором времени
        lvDates.invalidateViews();
        TimePickerDialog timePickerDialog = TimePickerDialog.
                newInstance(this,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
        timePickerDialog.setTitle(dialogTag);
        timePickerDialog.show(getFragmentManager(), dialogTag);
    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        // TODO повторение кода из onDataSet
        String dialogTag = view.getTag();
        DatePreposition key = DatePreposition.valueOf(dialogTag);
        Date oldDate = dates.get(key);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        dates.put(key, calendar.getTime());
        lvDates.invalidateViews();
    }


    public void onMapTypeClick(View view) {
        AlertDialog.Builder mapTypeDialog = new Builder(this);
        mapTypeDialog.setTitle("Choose map type");

        //TODO корявое копирование enum в String[]
        String[] mapTypesArrayStr = new String[MapType.values().length];
        for (int i = 0; i < MapType.values().length; i++) {
            mapTypesArrayStr[i] = MapType.values()[i].name();
        }

        mapTypeDialog.setSingleChoiceItems
                (mapTypesArrayStr, MapType.Normal.ordinal(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mInreachMapFragment.setMapType(MapType.values()[i]);
                            }
                        });
        mapTypeDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        mapTypeDialog.show();
    }


    public void onRefreshClick(View view) {
        if (mData != null) {
            mData.getData(null, null);
        }
    }


    @Override
    public void onDataReceived(List<InreachPoint> points) {
        mInreachMapFragment.drawPoints(points);
    }

}
