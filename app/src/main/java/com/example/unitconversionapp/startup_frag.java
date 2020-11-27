package com.example.unitconversionapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class startup_frag extends Fragment
{
    private static final String TAG =  "Startup Page";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.startup_fragment, container, false);
        //Finds image within startup_fragment.xml
        ImageView start_image = (ImageView) view.findViewById(R.id.Conversion_IC);

        //OnClick listener for image
        start_image.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view) {
            Log.v(TAG, "click");
            //Navigate to Home Fragment
            ((MainActivity)getActivity()).setViewPager(1);

        }
    });
        return view;


    }
}
