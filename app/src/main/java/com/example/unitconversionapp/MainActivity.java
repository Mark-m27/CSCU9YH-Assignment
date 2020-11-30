package com.example.unitconversionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PageAdapter pager;
    private ViewPager ViewPager;
    boolean success;

    //Values to SeedDatabase
    ConversionsModel  KGtoKG;
    ConversionsModel  KGtoPounds;
    ConversionsModel  KGtoStone;
    ConversionsModel  KGtoOunce;
    ConversionsModel  KGtoTonne;
    ConversionsModel  KGtoGram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = new PageAdapter(getSupportFragmentManager());
        ViewPager = (ViewPager) findViewById(R.id.container);
        //call view pager
        setupViewPager(ViewPager);

        //Create or Open Database
        DatabaseHelper db = new DatabaseHelper(this);
        success = db.seedDatabase();
        //Checks if Database has been succesfully created
        if ((success))
        {
            Toast.makeText(MainActivity.this, "Database Started...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Failed to Seed Database", Toast.LENGTH_SHORT).show();
        }



    }

    //Setup Pager
    private void setupViewPager(ViewPager viewPager)
    {
        //Adds all the fragments into the viewpager. Positions 0-8
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new startup_frag(), "Startup Fragment");
        adapter.addFragment(new home_frag(), "Home Fragment");
        adapter.addFragment(new weight_frag(), "Weight Fragment");
        adapter.addFragment(new speed_frag(), "Speed Fragment");
        adapter.addFragment(new distance_frag(), "Distance Fragment");
        adapter.addFragment(new time_frag(), "Time Fragment");
        adapter.addFragment(new volume_frag(), "Volume Fragment");
        adapter.addFragment(new storage_frag(), "Digital Storage Fragment");
        adapter.addFragment(new temp_frag(), "Temperature Fragment");
        viewPager.setAdapter(adapter);
    }

    //Takes in fragment position to allow for fragments to navigate between each other
    public void setViewPager(int fNumber)
    {
        //sets position to passed in number
        ViewPager.setCurrentItem(fNumber);
    }



}