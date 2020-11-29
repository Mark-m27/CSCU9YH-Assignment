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
import java.util.Map;

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

    //Reads Data from database and returns a hashmap containing required data
    public HashMap<String, ConversionsModel> getWeights()
    {
        HashMap<String, ConversionsModel> elementList = new HashMap<String, ConversionsModel>();
        String queryString = "Select * FROM " + WEIGHT_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            //loop through results and create model for each classifier
            do {
                int id = cursor.getInt(0);
                String classifier = cursor.getString(1);
                double multiplier = cursor.getDouble(2);

                ConversionsModel newModel = new ConversionsModel(id, classifier, multiplier);
                elementList.put(newModel.getClassifier(), newModel);
            }while(cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return elementList;
    }

    //Method to seed the database upon startup
    public boolean seedDatabase()
    {
        boolean added = false;
        try
        {
            //hashmap to add models into the database
            HashMap<String, ConversionsModel> weightList = new HashMap<String, ConversionsModel>();

            //KG Conversion Models
            ConversionsModel  KGtoKG= new ConversionsModel(0, "KGtoKG", 1);
            ConversionsModel  KGtoPounds= new ConversionsModel(1, "KGtoPounds", 2.205);
            ConversionsModel  KGtoStone= new ConversionsModel(2, "KGtoStone",0.157473);
            ConversionsModel  KGtoOunce= new ConversionsModel(3, "KGtoOunce", 35.274);
            ConversionsModel  KGtoTonne= new ConversionsModel(4, "KGtoTonne", 0.001);
            ConversionsModel  KGtoGram= new ConversionsModel(5, "KGtoGram", 1000);

            //Pounds Conversion Models
            ConversionsModel LBStoKG = new ConversionsModel("LBStoKG", 0.453592);
            ConversionsModel LBStoLBS = new ConversionsModel("LBStoLBS", 1);
            ConversionsModel LBStoStone = new ConversionsModel("LBStoStone", 0.0714286);
            ConversionsModel LBStoOunce = new ConversionsModel("LBStoOunce", 16);
            ConversionsModel LBStoTonne = new ConversionsModel("LBStoTonne", 0.000446429);
            ConversionsModel LBStoGram = new ConversionsModel("LBStoGram", 453.592);

            //Stone Conversion Models
            ConversionsModel StonetoKG = new ConversionsModel("StonetoKG", 6.35029);
            ConversionsModel StonetoLBS = new ConversionsModel("StonetoLBS", 14);
            ConversionsModel StonetoStone = new ConversionsModel("StonetoStone", 1);
            ConversionsModel StonetoOunce = new ConversionsModel("StonetoOunce", 224);
            ConversionsModel StonetoTonne = new ConversionsModel("StonetoTonne", 0.00625);
            ConversionsModel StonetoGram = new ConversionsModel("StonetoGram", 6350.29);

            //Stone Conversion Models
            ConversionsModel OuncetoKG = new ConversionsModel("OuncetoKG", 0.0283495);
            ConversionsModel OuncetoLBS = new ConversionsModel("OuncetoLBS", 0.0625);
            ConversionsModel OuncetoStone = new ConversionsModel("OuncetoStone", 0.00446429);
            ConversionsModel OuncetoOunce = new ConversionsModel("OuncetoOunce", 1);
            ConversionsModel OuncetoTonne = new ConversionsModel("OuncetoTonne", 2.83495503405e-5);
            ConversionsModel OuncetoGram = new ConversionsModel("OuncetoGram", 28.34955034049998801);

            //Stone Conversion Models
            ConversionsModel TonnetoKG = new ConversionsModel("TonnetoKG", 1016.0499996147);
            ConversionsModel TonnetoLBS = new ConversionsModel("TonnetoLBS", 2240.00681408);
            ConversionsModel TonnetoStone = new ConversionsModel("TonnetoStone", 160.00048672);
            ConversionsModel TonnetoOunce = new ConversionsModel("TonnetoOunce", 35840.10902528);
            ConversionsModel TonnetoTonne = new ConversionsModel("TonnetoTonne", 1);
            ConversionsModel TonnetoGram = new ConversionsModel("TonnetoGram", 1016049.9996147);

            //Stone Conversion Models
            ConversionsModel GramtoKG = new ConversionsModel("GramtoKG", 0.001000003042);
            ConversionsModel GramtoLBS = new ConversionsModel("GramtoLBS", 0.0022046293283108);
            ConversionsModel GramtoStone = new ConversionsModel("GramtoStone", 0.00015747352345077);
            ConversionsModel GramtoOunce = new ConversionsModel("GramtoOunce", 0.035274069252973);
            ConversionsModel GramtoTonne = new ConversionsModel("GramtoTonne", 9.842095215673e-7);
            ConversionsModel GramtoGram = new ConversionsModel("GramtoGram", 1);


            //Add them into the hashmap
            weightList.put(KGtoKG.getClassifier(), KGtoKG);
            weightList.put(KGtoPounds.getClassifier(), KGtoPounds);
            weightList.put(KGtoStone.getClassifier(), KGtoStone);
            weightList.put(KGtoOunce.getClassifier(), KGtoOunce);
            weightList.put(KGtoTonne.getClassifier(), KGtoTonne);
            weightList.put(KGtoGram.getClassifier(), KGtoGram);

            //Add them into the hashmap
            weightList.put(LBStoKG.getClassifier(), LBStoKG);
            weightList.put(LBStoLBS.getClassifier(), LBStoLBS);
            weightList.put(LBStoStone.getClassifier(), LBStoStone);
            weightList.put(LBStoOunce.getClassifier(), LBStoOunce);
            weightList.put(LBStoTonne.getClassifier(), LBStoTonne);
            weightList.put(LBStoGram.getClassifier(), LBStoGram);

            weightList.put(StonetoKG.getClassifier(), StonetoKG);
            weightList.put(StonetoLBS.getClassifier(), StonetoLBS);
            weightList.put(StonetoStone.getClassifier(), StonetoStone);
            weightList.put(StonetoOunce.getClassifier(), StonetoOunce);
            weightList.put(StonetoTonne.getClassifier(), StonetoTonne);
            weightList.put(StonetoGram.getClassifier(), StonetoGram);

            weightList.put(OuncetoKG.getClassifier(), OuncetoKG);
            weightList.put(OuncetoLBS.getClassifier(), OuncetoLBS);
            weightList.put(OuncetoStone.getClassifier(), OuncetoStone);
            weightList.put(OuncetoOunce.getClassifier(), OuncetoOunce);
            weightList.put(OuncetoTonne.getClassifier(), OuncetoTonne);
            weightList.put(OuncetoGram.getClassifier(), OuncetoGram);

            weightList.put(TonnetoKG.getClassifier(), TonnetoKG);
            weightList.put(TonnetoLBS.getClassifier(), TonnetoLBS);
            weightList.put(TonnetoStone.getClassifier(), TonnetoStone);
            weightList.put(TonnetoOunce.getClassifier(), TonnetoOunce);
            weightList.put(TonnetoTonne.getClassifier(), TonnetoTonne);
            weightList.put(TonnetoGram.getClassifier(), TonnetoGram);

            weightList.put(GramtoKG.getClassifier(), GramtoKG);
            weightList.put(GramtoLBS.getClassifier(), GramtoLBS);
            weightList.put(GramtoStone.getClassifier(), GramtoStone);
            weightList.put(GramtoOunce.getClassifier(), GramtoOunce);
            weightList.put(GramtoTonne.getClassifier(), GramtoTonne);
            weightList.put(GramtoGram.getClassifier(), GramtoGram);

            //For Each loop to and all models into database
            for (Map.Entry<String, ConversionsModel> entry : weightList.entrySet())
            {
                try
                {
                    addOne(entry.getValue());
                    added = true;
                }
                catch(Exception ex)
                {
                    added = false;
                    ex.printStackTrace();
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        //To tell main activity whether database bas seeded or not
        if (added)
        {
            return true;
        }else
        {
            return false;

        }
    }
    
}
