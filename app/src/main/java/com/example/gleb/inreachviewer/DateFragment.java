package com.example.gleb.inreachviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = DateFragment.class.getName();

    private static DateFragment fragmentInstance;
    private TextView tvDateFrom;
    private TextView tvDateTo;
    private Date mDateFrom;
    private Date mDateTo;
    public Data mInreachData;

    public static DateFragment getInstance() {
        if (fragmentInstance == null) {
            fragmentInstance = new DateFragment();
        }
        return fragmentInstance;
    }


    public DateFragment() {
        super();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_date, container, false);
        tvDateFrom = view.findViewById(R.id.tv_date_from);
        tvDateTo = view.findViewById(R.id.tv_date_to);
        updateDatesTextView();
        tvDateFrom.setOnClickListener(new DateOnClickListener(mDateFrom, "FROM"));
        tvDateTo.setOnClickListener(new DateOnClickListener(mDateTo, "TO"));
        Button btnGet = view.findViewById(R.id.btn_get);
        mInreachData = Data.getInstance(getActivity());
        mInreachData.getData(mDateFrom, mDateTo);
        btnGet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mInreachData.getData(mDateFrom, mDateTo);

            }
        });
        return view;
    }

    private void updateDatesTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar calendar = Calendar.getInstance();
        Log.i(TAG, "now = " + calendar.getTime().toString());
        if (mDateTo == null) {
            // Устанавливаем время на сегодняшюю сегодняшюю дату
//            calendar.set(Calendar.HOUR_OF_DAY, 22);
//            calendar.set(Calendar.MINUTE, 59);
//            calendar.set(Calendar.SECOND, 30);
//            mDateTo = calendar.getTime();
            tvDateTo.setText("null");
        }else {
            tvDateTo.setText(dateFormat.format(mDateTo));
        }
        if (mDateFrom == null) {
//            calendar.add(Calendar.MONTH, -2);
//            calendar.add(Calendar.DAY_OF_MONTH, -16);
//            calendar.set(Calendar.HOUR_OF_DAY, 00);
//            calendar.set(Calendar.MINUTE, 00);
//            calendar.set(Calendar.SECOND, 00);
//            mDateFrom = calendar.getTime();
            tvDateFrom.setText("null");
        } else {
            tvDateFrom.setText(dateFormat.format(mDateFrom));
        }
    }



    private class DateOnClickListener implements OnClickListener {
        private Date date;
        private String label;


        public DateOnClickListener(Date date, String label) {
            this.date = date;
            this.label = label;
        }

        @Override
        public void onClick(View view) {
            if (label == null) {
                return;
            }
            Calendar calendar = Calendar.getInstance();
            if (date==null) {
                date = calendar.getTime();
            } else {
                calendar.setTime(date);
            }

            DatePickerDialog datePickerDialog =
                    DatePickerDialog.newInstance(
                            DateFragment.getInstance(),
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    );

            Log.i(TAG, "date " + label + date.toString());
            datePickerDialog.show(getActivity().getFragmentManager(), label);

        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        if (view.getTag() == "FROM") {

            calendar.set(year, monthOfYear, dayOfMonth, 0, 0);
            mDateFrom = calendar.getTime();

        } else if (view.getTag() == "TO") {
            Log.i(TAG, "Selected date TO " + dayOfMonth + "/" + monthOfYear + "/" + year);
            calendar.set(year, monthOfYear, dayOfMonth, 23, 59);
            mDateTo = calendar.getTime();
        }
        updateDatesTextView();

    }
}
