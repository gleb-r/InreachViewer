package com.example.gleb.inreachviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

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

import java.util.List;


public class InreachMapFragment extends SupportMapFragment  {
    private static final String TAG = InreachMapFragment.class.getName();
    private static InreachMapFragment instance;
    private GoogleMap mGoogleMap;



    public InreachMapFragment() {
        // Required empty public constructor
    }

    public static InreachMapFragment newInstance() {
        if (instance == null) {
            instance = new InreachMapFragment();
        }

        return instance;
    }

    void setMapType (int mapType) {
        mGoogleMap.setMapType(mapType);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

    }

    @Override
    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        super.getMapAsync(onMapReadyCallback);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    public void drawPointsOnUIThread(final List<InreachPoint> points) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawPoints(points);

            }
        });
    }

    public void draw (List<InreachPoint> points) {
        Log.i(TAG, "num of points= "+points.size());
    }



    public void drawPoints(final List<InreachPoint> pointsList) {

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        BitmapDescriptor icon_point = BitmapDescriptorFactory.fromResource(R.drawable.marker_white);
        BitmapDescriptor icon_last_point = BitmapDescriptorFactory.fromResource(R.drawable.last_point_red);
        int tracksCount = 0;
        for (int i = 0; i < pointsList.size(); i++) {
            InreachPoint point = pointsList.get(i);
            if (i == 0) {
                tracksCount++;
            } else if (point.getEvent().contains("Tracking turned on from device")) {
                tracksCount++;
            }
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(point.getLatLng())
                    .title(point.getTimeLocal())
                    .snippet(point.getTag());
            if (i == pointsList.size() - 1) {
                float rotation = point.getCourse();
                markerOptions.icon(icon_last_point)
                        .rotation(rotation);
            }
            mGoogleMap.addMarker(markerOptions);
            boundsBuilder.include(point.getLatLng());
        }
        drawLinesBetweenPoints(pointsList, tracksCount);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newLatLngBounds(boundsBuilder.build(), width, height, 100);
        mGoogleMap.animateCamera(cameraUpdate);
    }


    private void drawLinesBetweenPoints(List<InreachPoint> points, final int tracksCount) {
        int baseAlfa = 55;
        int j = tracksCount-1;
        for (int i = 1; i < points.size(); i++) {
            InreachPoint prewPoint = points.get(i - 1);
            InreachPoint currentPoint = points.get(i);
            if (currentPoint.getEvent().contains("Tracking turned on from device")
                    || prewPoint.getEvent().contains("Tracking turned off from device")) {
                j--;
                continue;
            }
            PolylineOptions polyline = new PolylineOptions();
            // подбираем значение непрозрачности нарастающее с каждой следующей точкой
            int alfa = baseAlfa + (i + 1) * (200 / points.size());
            polyline.add(points.get(i - 1).getLatLng(), points.get(i).getLatLng())
                    .color(getLineColor(alfa,tracksCount,j))
                    .width(6);
            mGoogleMap.addPolyline(polyline);
        }
    }

    private int getLineColor(int alfa, int tracksCount, int j) {
        int trackPerColor = (tracksCount % 3 == 0) ? (tracksCount / 3) : (tracksCount / 3 + 1);
        // Чтобы цвета менялись более сглаженно, устанавливаем минимально значение
        if (trackPerColor < 3) {
            trackPerColor = 3;
        }
        int increment = 255 / trackPerColor;
        int red=0, green=0, blue=0;
        if (j==0){
            return Color.argb(alfa,0,0,0);
        }
        switch (j % 3) {
            case 0:
                red = increment * (j / 3 + 1);
                break;
            case 1:
                green = increment * (j / 3 + 1);
                break;
            case 2:
                blue = increment * (j / 3 + 1);
                break;
        }
        return Color.argb(alfa,red,green,blue);

    }



}





