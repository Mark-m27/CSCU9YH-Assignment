package com.example.unitconversionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class volume_frag extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Weight Fragment";
    String weightValue, unit;
    EditText weightInput;
    Button convert_btn;
    Button clear_btn;
    Spinner units;
    ListView units_list_view;
    ListView values_list_view;
    ArrayList<String> Conversions = new ArrayList<String>();
    //init Completed Conversion Values
    double millilitres;
    double litre;
    double pints;
    double gallon;

    DecimalFormat df = new DecimalFormat("0.000");
    DecimalFormat df2 = new DecimalFormat("0.00");

    //Database
    DatabaseHelper db = new DatabaseHelper(getActivity());





    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.volume_fragment, container, false);

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

        DatabaseHelper db = new DatabaseHelper(getActivity());

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
            public void onClick(View v) { Clear();
            }
        });

        //Setup Spinner
        String[] unitsList = getResources().getStringArray(R.array.volume_List);
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
        //Gets Value To Be converted from user
        weightValue = weightInput.getText().toString();
        DatabaseHelper db = new DatabaseHelper(getActivity());


        //The unit is converting from kilograms
        HashMap<String, ConversionsModel> weightConversions = db.getWeights();

        //Case Statement that allows the db to retrieve the correct multipliers for the conversion
        switch (unit)
        {
            case "Millilitres":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("MLtoML");
                    ConversionsModel b = weightConversions.get("MLtoLitre");
                    ConversionsModel c = weightConversions.get("MLtoPints");
                    ConversionsModel d = weightConversions.get("MLtoGallon");

                    //Calculate Conversions

                    millilitres = (Double.parseDouble(weightValue) * a.getMultiplier());
                    litre = (Double.parseDouble(weightValue) * b.getMultiplier());
                    pints = (Double.parseDouble(weightValue) * c.getMultiplier());
                    gallon = (Double.parseDouble(weightValue) * d.getMultiplier());

                    Conversions = populateValueList(millilitres, litre, pints, gallon);

                }

                catch (Exception ex)
                {
                  Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                  ex.printStackTrace();
                }

                break;
            case "Litre":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("LitretoML");
                    ConversionsModel b = weightConversions.get("LitretoLitre");
                    ConversionsModel c = weightConversions.get("LitretoPints");
                    ConversionsModel d = weightConversions.get("LitretoGallon");

                    //Calculate Conversions

                    millilitres = (Double.parseDouble(weightValue) * a.getMultiplier());
                    litre = (Double.parseDouble(weightValue) * b.getMultiplier());
                    pints = (Double.parseDouble(weightValue) * c.getMultiplier());
                    gallon = (Double.parseDouble(weightValue) * d.getMultiplier());

                    Conversions = populateValueList(millilitres, litre, pints, gallon);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

                break;
            case "Pints":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("PintstoML");
                    ConversionsModel b = weightConversions.get("PintstoLitre");
                    ConversionsModel c = weightConversions.get("PintstoPints");
                    ConversionsModel d = weightConversions.get("PintstoGallon");

                    //Calculate Conversions

                    millilitres = (Double.parseDouble(weightValue) * a.getMultiplier());
                    litre = (Double.parseDouble(weightValue) * b.getMultiplier());
                    pints = (Double.parseDouble(weightValue) * c.getMultiplier());
                    gallon = (Double.parseDouble(weightValue) * d.getMultiplier());

                    Conversions = populateValueList(millilitres, litre, pints, gallon);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                break;
            case "Gallon":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("GallontoML");
                    ConversionsModel b = weightConversions.get("GallontoLitre");
                    ConversionsModel c = weightConversions.get("GallontoPints");
                    ConversionsModel d = weightConversions.get("GallontoGallon");

                    //Calculate Conversions

                    millilitres = (Double.parseDouble(weightValue) * a.getMultiplier());
                    litre = (Double.parseDouble(weightValue) * b.getMultiplier());
                    pints = (Double.parseDouble(weightValue) * c.getMultiplier());
                    gallon = (Double.parseDouble(weightValue) * d.getMultiplier());

                    Conversions = populateValueList(millilitres, litre, pints, gallon);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                break;

        }

        //Sets List View to correct
        ArrayAdapter valueAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, Conversions);
        values_list_view.setAdapter(valueAdapter);
        //Toast.makeText(getActivity(), String.valueOf(weightValue) + " " + unit, Toast.LENGTH_SHORT).show();
    }

    //Required Methods for implementing onItemSelectedListener
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

    //Clears Contents of EditText
    public void Clear()
    {
        weightInput.setText("");
        Conversions.clear();
        values_list_view.setAdapter(null);
    }

    //This method takes in the 6 conversions and adds them to a list to be displayed in list view
    public ArrayList populateValueList(double a, double b, double c, double d)
    {
        double[] valueList = new double[4];
        //Test value list view
        valueList[0] = Double.parseDouble(df.format(a));
        valueList[1] = Double.parseDouble(df.format(b));
        valueList[2] = Double.parseDouble(df.format(c));
        valueList[3] = Double.parseDouble(df2.format(d));

        ArrayList<String> arrayList = new ArrayList<String>();
        for (double s : valueList)
        {
            arrayList.add(String.valueOf(s));
        }
        return arrayList;
    }
}