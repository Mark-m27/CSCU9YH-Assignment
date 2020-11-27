package com.example.unitconversionapp;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class weight_frag extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Weight Fragment";
    String weightValue, unit;
    EditText weightInput;
    Button convert_btn;
    Button clear_btn;
    Spinner units;
    ListView units_list_view;
    ListView values_list_view;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weight_fragment, container, false);

        //Setup Back Button for easy navigation
        ImageView back_btn = (ImageView) view.findViewById(R.id.BackBtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        //Finds items from fragment and sets as variable
        weightInput = (EditText) view.findViewById(R.id.user_input);
        units = (Spinner) view.findViewById(R.id.units_spinner);
        units.setOnItemSelectedListener(this);
        convert_btn = (Button) view.findViewById(R.id.convert_btn);
        clear_btn = (Button) view.findViewById(R.id.clear_btn);
        units_list_view = (ListView) view.findViewById(R.id.unit_listView);
        values_list_view = (ListView) view.findViewById(R.id.value_listView);


        //Set OnClick Listener for Convert Button
        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Convert();
            }
        });

        //Set OnClick Listener for Clear Button
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getActivity());
                List<ConversionsModel> model = db.getKG();
                Toast.makeText(getActivity(), model.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //Setup Spinner
        String[] unitsList = getResources().getStringArray(R.array.units_List);
        ArrayAdapter dropdownadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, unitsList);
        dropdownadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(dropdownadapter);

        //Setup List View
        ArrayAdapter listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,unitsList);
        units_list_view.setAdapter(listAdapter);

        return view;
    }

    public void Convert()
    {
        weightValue = weightInput.getText().toString();


        //ArrayAdapter valueAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        //values_list_view.setAdapter(valueAdapter);
        Toast.makeText(getActivity(), String.valueOf(weightValue) + " " + unit, Toast.LENGTH_SHORT).show();
    }

    //Required Methods for implmenting onItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Gets item at current position of spinner
        if (parent.getId() == R.id.units_spinner)
        {
            //Sets String name to unit that is being converted from
            unit = parent.getItemAtPosition(position).toString();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
            //Test value list view
        int[] valueList = new int[6];
              valueList[0] = 98;
              valueList[1] = 32;
              valueList[2] = 56;
              valueList[3] = 77;
              valueList[4] = 67;
              valueList[5] = 88;

        ArrayList<String> arrayList = new ArrayList<String>();
        for(int s:valueList) {
            arrayList.add(String.valueOf(s));
        }
     */
}