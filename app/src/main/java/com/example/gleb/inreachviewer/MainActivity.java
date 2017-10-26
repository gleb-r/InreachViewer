package com.example.gleb.inreachviewer;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
                LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
                BitmapDescriptor icon_point = BitmapDescriptorFactory.fromResource(R.drawable.marker_white);
                BitmapDescriptor icon_last_point = BitmapDescriptorFactory.fromResource(R.drawable.last_point_red);
                for (int i = 0; i < pointsList.size(); i++) {
                    InreachPoint point = pointsList.get(i);

                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(point.getLatLng())
                            .title(point.getTimeLocal())
                            .snippet(point.getTag());
                    if (i == pointsList.size() - 1) {
                        float rotation = point.getCourse();
                        markerOptions.icon(icon_last_point)
                                .rotation(rotation);
                    }
                    mMap.addMarker(markerOptions);
                    boundsBuilder.include(point.getLatLng());
                }
                drawLinesBetweenPoints(pointsList);
                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;
                CameraUpdate cameraUpdate = CameraUpdateFactory
                        .newLatLngBounds(boundsBuilder.build(), width, height, 100);
                mMap.animateCamera(cameraUpdate);
            }
        });

    }

    private void drawLinesBetweenPoints(List<InreachPoint> points) {

        for (int i = 1; i < points.size(); i++) {
            InreachPoint prewPoint = points.get(i - 1);
            InreachPoint currentPoint = points.get(i);
            if (currentPoint.getEvent().contains("Tracking turned on from device")
                    || prewPoint.getEvent().contains("Tracking turned off from device")) {
                continue;
            }
            PolylineOptions polyline = new PolylineOptions();
            polyline.add(points.get(i - 1).getLatLng(), points.get(i).getLatLng())
                    .color(Color.GRAY)
                    .width(6);

            mMap.addPolyline(polyline);
        }
    }


    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            Date d1 = null;
            try {
                d1 = dateFormat.parse("2017-09-19 09:30");
            } catch (ParseException e) {
                Log.e(TAG, "Failed parse", e);
            }
            String kmlStr = new InreachApi().fetchPoints(d1, Calendar.getInstance().getTime());
            try {
                mPointsList = InreachKmlParser.parse(kmlStr);
                for (InreachPoint point : mPointsList) {
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
            drawPoints(mPointsList);
            return null;
        }
    }
}
