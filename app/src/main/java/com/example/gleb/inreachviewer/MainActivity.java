package com.example.gleb.inreachviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private String mTempStr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new FetchItemsTask().execute();

        try {
            XmlPullParser mParser = getResources().getXml(R.xml.people);
            while (mParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (mParser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "START DOCUMENT");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "START TAG: tag name = " + mParser.getName()
                                + ", depth = " + mParser.getDepth() + ", attributes count = "
                                + mParser.getAttributeCount());
                        mTempStr = "";
                        for (int i = 0; i < mParser.getAttributeCount(); i++) {
                            mTempStr += mParser.getAttributeName(i) + " = "
                                    + mParser.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(mTempStr)) {
                            Log.i(TAG, "Attributes: " + mTempStr);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i(TAG, "END TAG: name = " + mParser.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(TAG, "text = " + mParser.getText());
                        break;
                    default:
                        break;

                }
                mParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            Date d1 = null;
            try {
                d1 = dateFormat.parse("2017-10-19 13:30");
            } catch (ParseException e) {
                Log.e(TAG, "Failed parse", e);
            }
            new InreachApi().fetchPoints(d1, Calendar.getInstance().getTime());
            return null;
        }
    }
}
