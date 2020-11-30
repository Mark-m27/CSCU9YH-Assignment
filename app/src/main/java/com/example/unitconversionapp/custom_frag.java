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

public class custom_frag extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Weight Fragment";
    String weightValue, unit;
    EditText weightInput;
    EditText classifier_txt;
    EditText multiplier_txt;
    String classifer;
    double multiplier;
    Button convert_btn;
    Button clear_btn;
    Button add_btn;
    Spinner units;
    ListView units_list_view;
    ListView values_list_view;
    ArrayList<String> Conversions = new ArrayList<String>();
    //init Completed Conversion Values
    double customVariable1;
    double customVariable2;
    double customVariable3;
    double customVariable4;

    ArrayList<ConversionsModel> weightConversions;


    DecimalFormat df = new DecimalFormat("0.000");
    DecimalFormat df2 = new DecimalFormat("0.00");

    //Database
    DatabaseHelper db = new DatabaseHelper(getActivity());
    ArrayList<String> unitsList = new ArrayList<String>();





    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_fragment, container, false);

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
        classifier_txt = (EditText) view.findViewById(R.id.classifier_txt);
        multiplier_txt = (EditText) view.findViewById(R.id.multiplier_txt);
        units = (Spinner) view.findViewById(R.id.units_spinner);
        units.setOnItemSelectedListener(this);
        convert_btn = (Button) view.findViewById(R.id.convert_btn);
        clear_btn = (Button) view.findViewById(R.id.clear_btn);
        add_btn = (Button) view.findViewById(R.id.add_btn);
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

        //Set OnClick Listener for add button
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });

        ArrayList<ConversionsModel> customConversions = db.getCustoms();

        for (ConversionsModel key : customConversions)
        {
            unitsList.add(key.getClassifier());
        }

        ArrayAdapter dropdownadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, unitsList);
        dropdownadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(dropdownadapter);

        //Setup List View
        ArrayAdapter listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,unitsList);
        units_list_view.setAdapter(listAdapter);

        return view;
    }

    public void Add()
    {
        try
        {
            //Get Classifier and Multiplier from User Input
            classifer = classifier_txt.getText().toString();
            multiplier = Double.parseDouble(multiplier_txt.getText().toString());

            //Create New Instance of a conversion from user input
            ConversionsModel newModel = new ConversionsModel(classifer, multiplier);

            //Add New Instance of Data to the database
            DatabaseHelper db = new DatabaseHelper(getActivity());
            db.addtoCustom(newModel);

            ArrayList<ConversionsModel> dbCustoms = db.getCustoms();
            //Toast.makeText(getActivity(), String.valueOf(customConversions.values()), Toast.LENGTH_SHORT).show();

            //Setup Spinner
            //Loop to get the key name from the hash table and place it in the units list view and spinner options

            for (ConversionsModel key : dbCustoms)
            {
                unitsList.add(key.getClassifier());
            }

            ArrayAdapter dropdownadapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, unitsList);
            dropdownadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            units.setAdapter(dropdownadapter);

            //Setup List View
            ArrayAdapter listAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,unitsList);
            units_list_view.setAdapter(listAdapter);

        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    public void Convert()
    {
        //Gets Value To Be converted from user
        weightValue = weightInput.getText().toString();
        DatabaseHelper db = new DatabaseHelper(getActivity());


        //The unit is converting from kilograms
        weightConversions = db.getCustoms();

        //Hashmap to get the individual values from the array list
        HashMap<String,ConversionsModel> conversionsModelHashMap = new HashMap<String, ConversionsModel>();
        //For Each Loop
        for(ConversionsModel m : weightConversions)
        {
            conversionsModelHashMap.put(m.getClassifier(), m);
        }

        try
        {
            //Get Required multipliers from the database
            ConversionsModel a = conversionsModelHashMap.get(unitsList.get(0));


            //Calculate Conversions
            customVariable1 = (Double.parseDouble(weightValue) * a.getMultiplier());


            Conversions = populateValueList(customVariable1);
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
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
    public ArrayList populateValueList(double a)
    {
        double[] valueList = new double[1];
        //Test value list view
        valueList[0] = Double.parseDouble(df.format(a));

        ArrayList<String> arrayList = new ArrayList<String>();
        for (double s : valueList)
        {
            arrayList.add(String.valueOf(s));
        }
        return arrayList;
    }
}