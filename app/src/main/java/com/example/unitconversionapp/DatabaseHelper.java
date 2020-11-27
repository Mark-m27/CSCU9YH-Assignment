package com.example.unitconversionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Conversion.DB";
    public static final String WEIGHT_TABLE = "WEIGHT_TABLE";
    public static final String COLUMN_CLASSIFIER = "CLASSIFIER";
    public static final String COLUMN_MULTIPLIER = "MULTIPLIER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement = "CREATE TABLE " + WEIGHT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CLASSIFIER + " TEXT, " + COLUMN_MULTIPLIER + " REAL)";
        db.execSQL(createTableStatement);
        seedDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

    public boolean addOne(ConversionsModel model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLASSIFIER, model.getClassifier());
        cv.put(COLUMN_MULTIPLIER, model.getMultiplier());

        long insert = db.insert(WEIGHT_TABLE, null, cv);
        if (insert == -1 ) {
            return false;
        }
        else if (insert == 1)
        {
            return true;
        }
        return false;
    }

    //Possibly not required
    //returns all values within database
    public List<ConversionsModel> getKG()
    {
        List<ConversionsModel> elementList = new ArrayList<>();
        String queryString = "Select * FROM " + WEIGHT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            //loop through results and create model for each classifier
            do {
                int id = cursor.getInt(0);
                String classifier = cursor.getString(1);
                double multiplier = cursor.getDouble(2);

                ConversionsModel newModel = new ConversionsModel(id, classifier, multiplier);
                elementList.add(newModel);
            }while(cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return elementList;
    }

    private void seedDatabase()
    {
        try
        {
            HashMap<String, Double> modelList = new HashMap<String, Double>();
            modelList.put("KGtoKG", (Double) 1);
            //Create the items within database the app will use
            ConversionsModel  KGtoKG= new ConversionsModel(0, "KGtoKG", 1);
            ConversionsModel  KGtoPounds= new ConversionsModel(1, "KGtoPounds", 2.205);
            ConversionsModel  KGtoStone= new ConversionsModel(2, "KGtoStone",0.157473);
            ConversionsModel  KGtoOunce= new ConversionsModel(3, "KGtoOunce", 35.274);
            ConversionsModel  KGtoTonne= new ConversionsModel(4, "KGtoTonne", 0.001);
            ConversionsModel  KGtoGram= new ConversionsModel(5, "KGtoGram", 1000);
        ];
            SQLiteDatabase db = this.getWritableDatabase();
            for (i = 0; i <)
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
