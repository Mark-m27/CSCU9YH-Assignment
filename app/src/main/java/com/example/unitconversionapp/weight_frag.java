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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    ArrayList<String> Conversions = new ArrayList<String>();
    //init Completed Conversion Values
    double kg;
    double pounds;
    double stone;
    double ounce;
    double tonne;
    double gram;

    DecimalFormat df = new DecimalFormat("0.000");
    DecimalFormat df2 = new DecimalFormat("0.00");

    //Database
    DatabaseHelper db = new DatabaseHelper(getActivity());





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
        //Gets Value To Be converted from user
        weightValue = weightInput.getText().toString();
        DatabaseHelper db = new DatabaseHelper(getActivity());


        //The unit is converting from kilograms
        HashMap<String, ConversionsModel> weightConversions = db.getWeights();

        //Case Statement that allows the db to retrieve the correct multipliers for the conversion
        switch (unit)
        {
            case "Kilograms":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("KGtoKG");
                    ConversionsModel b = weightConversions.get("KGtoPounds");
                    ConversionsModel c = weightConversions.get("KGtoStone");
                    ConversionsModel d = weightConversions.get("KGtoOunce");
                    ConversionsModel e = weightConversions.get("KGtoTonne");
                    ConversionsModel f = weightConversions.get("KGtoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

                }

                catch (Exception ex)
                {
                  Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                  ex.printStackTrace();
                }

                break;
            case "Pounds":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("LBStoKG");
                    ConversionsModel b = weightConversions.get("LBStoLBS");
                    ConversionsModel c = weightConversions.get("LBStoStone");
                    ConversionsModel d = weightConversions.get("LBStoOunce");
                    ConversionsModel e = weightConversions.get("LBStoTonne");
                    ConversionsModel f = weightConversions.get("LBStoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

                break;
            case "Stone":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("StonetoKG");
                    ConversionsModel b = weightConversions.get("StonetoLBS");
                    ConversionsModel c = weightConversions.get("StonetoStone");
                    ConversionsModel d = weightConversions.get("StonetoOunce");
                    ConversionsModel e = weightConversions.get("StonetoTonne");
                    ConversionsModel f = weightConversions.get("StonetoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                break;
            case "Ounce":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("OuncetoKG");
                    ConversionsModel b = weightConversions.get("OuncetoLBS");
                    ConversionsModel c = weightConversions.get("OuncetoStone");
                    ConversionsModel d = weightConversions.get("OuncetoOunce");
                    ConversionsModel e = weightConversions.get("OuncetoTonne");
                    ConversionsModel f = weightConversions.get("OuncetoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                break;
            case "Tonne":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("TonnetoKG");
                    ConversionsModel b = weightConversions.get("TonnetoLBS");
                    ConversionsModel c = weightConversions.get("TonnetoStone");
                    ConversionsModel d = weightConversions.get("TonnetoOunce");
                    ConversionsModel e = weightConversions.get("TonnetoTonne");
                    ConversionsModel f = weightConversions.get("TonnetoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

                }

                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
                break;
            case"Gram":
                try {
                    //Get required multipliers from database
                    ConversionsModel a = weightConversions.get("GramtoKG");
                    ConversionsModel b = weightConversions.get("GramtoLBS");
                    ConversionsModel c = weightConversions.get("GramtoStone");
                    ConversionsModel d = weightConversions.get("GramtoOunce");
                    ConversionsModel e = weightConversions.get("GramtoTonne");
                    ConversionsModel f = weightConversions.get("GramtoGram");

                    //Calculate Conversions

                    kg = (Double.parseDouble(weightValue) * a.getMultiplier());
                    pounds = (Double.parseDouble(weightValue) * b.getMultiplier());
                    stone = (Double.parseDouble(weightValue) * c.getMultiplier());
                    ounce = (Double.parseDouble(weightValue) * d.getMultiplier());
                    tonne = (Double.parseDouble(weightValue) * e.getMultiplier());
                    gram = (Double.parseDouble(weightValue) * f.getMultiplier());

                    Conversions = populateValueList(kg, pounds, stone, ounce, tonne, gram);

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
    public ArrayList populateValueList(double a, double b, double c, double d, double e, double f)
    {
        double[] valueList = new double[6];
        //Test value list view
        valueList[0] = Double.parseDouble(df.format(a));
        valueList[1] = Double.parseDouble(df.format(b));
        valueList[2] = Double.parseDouble(df.format(c));
        valueList[3] = Double.parseDouble(df2.format(d));
        valueList[4] = Double.parseDouble(df.format(e));
        valueList[5] = Double.parseDouble(df.format(f));

        ArrayList<String> arrayList = new ArrayList<String>();
        for (double s : valueList)
        {
            arrayList.add(String.valueOf(s));
        }
        return arrayList;
    }
}