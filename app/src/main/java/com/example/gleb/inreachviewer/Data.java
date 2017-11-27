package com.example.gleb.inreachviewer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Gleb on 31.10.2017.
 * Singletone class getting and
 * stores List of InreachPoints
 */

class Data  {
    private static Data instance;
    private static final String TAG = Data.class.getName();

    interface DataCallBack {
        void onDataReceived(List<InreachPoint> points);
    }

    private DataCallBack mDataCallBack;

    private List<InreachPoint> mPoints;
    private Date mDateFrom;
    private Date mDateTo;
    private Context mContext;

    private Data(Context context) {
        this.mContext = context;
        this.mDataCallBack = (DataCallBack)context;
    }


    static Data getInstance(Context context) {
        if (instance==null) {
            instance = new Data(context);
        }
        return instance;
    }


    public void getData(Date dateFrom, Date dateTo) {
        mDateFrom = dateFrom;
        mDateTo = dateTo;
        new FetchItemsTask().execute();
    }


    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String kmlStr = new InreachApi().fetchPoints(mDateFrom, mDateTo);
            try {
                mPoints = KmlParser.parse(kmlStr);
                for (InreachPoint point : mPoints) {
                     Log.i(TAG, point.toString() + "\n");
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mDataCallBack.onDataReceived(mPoints);
        }
    }
}
