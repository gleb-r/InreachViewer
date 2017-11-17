package com.example.gleb.inreachviewer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

public class MainActivity extends FragmentActivity implements Data.DataCallBack

{
    private static final String TAG = MainActivity.class.getName();
    public InreachMapFragment mInreachMapFragment;

    ConstraintLayout mConstraintLayout;
    BottomSheetBehavior mBottomSheetBehavior;
    FloatingActionButton mFabSettings;

    Data mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


//        Fragment fragmentDate = fragmentManager.findFragmentById(R.id.date_fragment_container);
//        if (fragmentDate == null) {
//            fragmentDate = DateFragment.getInstance();
//            fragmentManager
//                    .beginTransaction()
//                    .add(R.id.date_fragment_container, fragmentDate)
//                    .commit();
//        }

    }

    public void onMapTypeClick  (View view) {
        switch (view.getId()) {
            case R.id.btnNormal:
                mInreachMapFragment.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.btnHybrid:
                mInreachMapFragment.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.btnTerrain:
                mInreachMapFragment.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;

        }
    }

    public void onRefreshClick(View view) {
        if (mData != null) {
            mData.getData(null,null);
        }

    }


    @Override
    public void onDataReceived(List<InreachPoint> points) {

        Toast.makeText(this, "on Data Received Activity", Toast.LENGTH_LONG).show();
        mInreachMapFragment.drawPoints(points);
    }
}
