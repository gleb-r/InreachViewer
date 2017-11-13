package com.example.gleb.inreachviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends FragmentActivity implements Data.DataCallBack

{
    private static final String TAG = MainActivity.class.getName();
    public InreachMapFragment mInreachMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentMap = fragmentManager.findFragmentById(R.id.map_fragment_container);
        if (fragmentMap == null) {
            fragmentMap = InreachMapFragment.newInstance();
            mInreachMapFragment = (InreachMapFragment)fragmentMap;
            fragmentManager
                    .beginTransaction()
                    .add(R.id.map_fragment_container, fragmentMap)
                    .commit();
        }
        Fragment fragmentDate = fragmentManager.findFragmentById(R.id.date_fragment_container);
        if (fragmentDate == null) {
            fragmentDate = DateFragment.getInstance();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.date_fragment_container, fragmentDate)
                    .commit();
        }

    }

    @Override
    public void onDataReceived(List<InreachPoint> points) {
        InreachMapFragment mapFragment = (InreachMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment_container);
        Toast.makeText(this, "on Data Received Activity", Toast.LENGTH_LONG).show();
        mapFragment.drawPoints(points);
    }
}
