package com.example.gleb.inreachviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = MainActivity.class.getName();
    private GoogleMap mMap;
    private List<InreachPoint> mPointsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        FetchItemsTask fetchItemsTask = new FetchItemsTask();
        fetchItemsTask.execute();
    }

    private void drawPoints(final List<InreachPoint> pointsList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PolylineOptions polylineOptions = new PolylineOptions();
                LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
                for (InreachPoint point : pointsList) {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(point.getLatLng())
                            .title(point.getTimeLocal().toString())
                            .snippet(point.getTag());
                    mMap.addMarker(markerOptions);
                    polylineOptions.add(point.getLatLng());
                    boundsBuilder.include(point.getLatLng());
                }
                mMap.addPolyline(polylineOptions);
                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;
                CameraUpdate cameraUpdate = CameraUpdateFactory
                        .newLatLngBounds(boundsBuilder.build(), width, height, 100);
                mMap.animateCamera(cameraUpdate);
            }
        });

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
            String kmlStr = new InreachApi().fetchPoints(d1, Calendar.getInstance().getTime());
            try {
                mPointsList = InreachKmlParser.parse(kmlStr);
                for (InreachPoint point: mPointsList) {
                    Log.i(TAG,point.toString()+ "\n");
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
            drawPoints(mPointsList);
            return null;
        }
    }
}
