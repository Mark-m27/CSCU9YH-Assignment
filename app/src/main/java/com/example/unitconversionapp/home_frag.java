package com.example.unitconversionapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class home_frag extends Fragment {
    private static final String TAG = "Home Page";

    private TableRow weight_table_btn;
    private TableRow speed_table_btn;
    private TableRow distance_table_btn;
    private TableRow time_table_btn;
    private TableRow volume_table_btn;
    private TableRow temp_table_btn;
    private TableRow dgStorage_table_btn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //Sets up all TableRows with OnClick Listeners
        //This allows the user to tap the row like a button
        //And Changes the viewpager to the correct fragment
        weight_table_btn = (TableRow) view.findViewById(R.id.WeightFragment);
        weight_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        speed_table_btn = (TableRow) view.findViewById(R.id.SpeedFragment);
        speed_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });

        distance_table_btn = (TableRow) view.findViewById(R.id.DistanceFragment);
        distance_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(4);
            }
        });

        time_table_btn = (TableRow) view.findViewById(R.id.TimeFragment);
        time_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(5);
            }
        });

        volume_table_btn = (TableRow) view.findViewById(R.id.VolumeFragment);
        volume_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(6);
            }
        });

        temp_table_btn = (TableRow) view.findViewById(R.id.TempFragment);
        temp_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(7);
            }
        });

        dgStorage_table_btn = (TableRow) view.findViewById(R.id.StorageFragment);
        dgStorage_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(8);
            }
        });
        return view;
    }

}