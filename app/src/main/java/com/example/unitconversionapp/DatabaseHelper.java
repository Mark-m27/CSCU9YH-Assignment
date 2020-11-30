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
            HashMap<String, ConversionsModel> convList = new HashMap<String, ConversionsModel>();

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

            //Ounce Conversion Models
            ConversionsModel OuncetoKG = new ConversionsModel("OuncetoKG", 0.0283495);
            ConversionsModel OuncetoLBS = new ConversionsModel("OuncetoLBS", 0.0625);
            ConversionsModel OuncetoStone = new ConversionsModel("OuncetoStone", 0.00446429);
            ConversionsModel OuncetoOunce = new ConversionsModel("OuncetoOunce", 1);
            ConversionsModel OuncetoTonne = new ConversionsModel("OuncetoTonne", 2.83495503405e-5);
            ConversionsModel OuncetoGram = new ConversionsModel("OuncetoGram", 28.34955034049998801);

            //Tonne Conversion Models
            ConversionsModel TonnetoKG = new ConversionsModel("TonnetoKG", 1016.0499996147);
            ConversionsModel TonnetoLBS = new ConversionsModel("TonnetoLBS", 2240.00681408);
            ConversionsModel TonnetoStone = new ConversionsModel("TonnetoStone", 160.00048672);
            ConversionsModel TonnetoOunce = new ConversionsModel("TonnetoOunce", 35840.10902528);
            ConversionsModel TonnetoTonne = new ConversionsModel("TonnetoTonne", 1);
            ConversionsModel TonnetoGram = new ConversionsModel("TonnetoGram", 1016049.9996147);

            //Gram Conversion Models
            ConversionsModel GramtoKG = new ConversionsModel("GramtoKG", 0.001000003042);
            ConversionsModel GramtoLBS = new ConversionsModel("GramtoLBS", 0.0022046293283108);
            ConversionsModel GramtoStone = new ConversionsModel("GramtoStone", 0.00015747352345077);
            ConversionsModel GramtoOunce = new ConversionsModel("GramtoOunce", 0.035274069252973);
            ConversionsModel GramtoTonne = new ConversionsModel("GramtoTonne", 9.842095215673e-7);
            ConversionsModel GramtoGram = new ConversionsModel("GramtoGram", 1);


            //SPEED CONVERSION MODELS
            ConversionsModel MPHtoMPH = new ConversionsModel("MPHtoMPH", 1);
            ConversionsModel MPHtoKPH = new ConversionsModel("MPHtoKPH", 1.60934);
            ConversionsModel MPHtoMS = new ConversionsModel("MPHtoMS", 0.44704);
            ConversionsModel MPHtoKnots = new ConversionsModel("MPHtoKnots", 0.868976);
            
            ConversionsModel KPHtoMPH = new ConversionsModel("KPHtoMPH", 0.621371);
            ConversionsModel KPHtoKPH = new ConversionsModel("KPHtoKPH", 1);
            ConversionsModel KPHtoMS = new ConversionsModel("KPHtoMS", 0.277778);
            ConversionsModel KPHtoKnots = new ConversionsModel("KPHtoKnots", 0.539957);

            ConversionsModel MStoMPH = new ConversionsModel("MStoMPH", 2.23694);
            ConversionsModel MStoKPH = new ConversionsModel("MStoKPH", 3.6);
            ConversionsModel MStoMS = new ConversionsModel("MStoMS", 1);
            ConversionsModel MStoKnots = new ConversionsModel("MStoKnots", 1.94384);

            ConversionsModel KnotstoMPH = new ConversionsModel("KnotstoMPH", 1.15078);
            ConversionsModel KnotstoKPH = new ConversionsModel("KnotstoKPH", 1.852);
            ConversionsModel KnotstoMS = new ConversionsModel("KnotstoMS", 0.514444);
            ConversionsModel KnotstoKnots = new ConversionsModel("KnotstoKnots", 1);

            //DISTANCE CONVERSION MODELS
            ConversionsModel CMtoCM = new ConversionsModel("CMtoCM",1);
            ConversionsModel CMtoMM = new ConversionsModel("CMtoMM",10);
            ConversionsModel CMtoFeet = new ConversionsModel("CMtoFeet",0.0328084);
            ConversionsModel CMtoMiles = new ConversionsModel("CMtoMiles",6.2137e-6);
            ConversionsModel CMtoKM = new ConversionsModel("CMtoKM",1e-5);

            ConversionsModel MMtoCM = new ConversionsModel("MMtoCM",0.1);
            ConversionsModel MMtoMM = new ConversionsModel("MMtoMM",1);
            ConversionsModel MMtoFeet = new ConversionsModel("MMtoFeet",0.00328084);
            ConversionsModel MMtoMiles = new ConversionsModel("MMtoMiles",6.2137e-7);
            ConversionsModel MMtoKM = new ConversionsModel("MMtoKM",1e-6);

            ConversionsModel FeettoCM = new ConversionsModel("FeettoCM",30.48);
            ConversionsModel FeettoMM = new ConversionsModel("FeettoMM",304.8);
            ConversionsModel FeettoFeet = new ConversionsModel("FeettoFeet",1);
            ConversionsModel FeettoMiles = new ConversionsModel("FeettoMiles",0.000189394);
            ConversionsModel FeettoKM = new ConversionsModel("FeettoKM",0.0003048);

            ConversionsModel MilestoCM = new ConversionsModel("MilestoCM",160934);
            ConversionsModel MilestoMM = new ConversionsModel("MilestoMM",1.609e+6);
            ConversionsModel MilestoFeet = new ConversionsModel("MilestoFeet",5280);
            ConversionsModel MilestoMiles = new ConversionsModel("MilestoMiles",1);
            ConversionsModel MilestoKM = new ConversionsModel("MilestoKM",1.60934);

            ConversionsModel KMtoCM = new ConversionsModel("KMtoCM",100000);
            ConversionsModel KMtoMM = new ConversionsModel("KMtoMM",1e+6);
            ConversionsModel KMtoFeet = new ConversionsModel("KMtoFeet",3280.84);
            ConversionsModel KMtoMiles = new ConversionsModel("KMtoMiles",0.621371);
            ConversionsModel KMtoKM = new ConversionsModel("KMtoKM",1);

            //TIME CONVERSION MODELS
            ConversionsModel SecondstoSeconds = new ConversionsModel("SecondstoSeconds",1);
            ConversionsModel SecondstoMinutes = new ConversionsModel("SecondstoMinutes",0.0166667);
            ConversionsModel SecondstoHours = new ConversionsModel("SecondstoHours",0.00027777833333);
            ConversionsModel SecondstoDays = new ConversionsModel("SecondstoDays",1.1574e-5);
            ConversionsModel SecondstoWeeks = new ConversionsModel("SecondstoWeeks",1.6534e-6);

            ConversionsModel MinutestoSeconds = new ConversionsModel("MinutestoSeconds",60);
            ConversionsModel MinutestoMinutes = new ConversionsModel("MinutestoMinutes",1);
            ConversionsModel MinutestoHours = new ConversionsModel("MinutestoHours",0.0166667);
            ConversionsModel MinutestoDays = new ConversionsModel("MinutestoDays",0.000694444);
            ConversionsModel MinutestoWeeks = new ConversionsModel("MinutestoWeeks",9.9206e-5);

            ConversionsModel HourstoSeconds = new ConversionsModel("HourstoSeconds",3600);
            ConversionsModel HourstoMinutes = new ConversionsModel("HourstoMinutes",60);
            ConversionsModel HourstoHours = new ConversionsModel("HourstoHours",1);
            ConversionsModel HourstoDays = new ConversionsModel("HourstoDays",0.0416667);
            ConversionsModel HourstoWeeks = new ConversionsModel("HourstoWeeks",0.00595238);

            ConversionsModel DaystoSeconds = new ConversionsModel("DaystoSeconds",86400);
            ConversionsModel DaystoMinutes = new ConversionsModel("DaystoMinutes",1440);
            ConversionsModel DaystoHours = new ConversionsModel("DaystoHours",24);
            ConversionsModel DaystoDays = new ConversionsModel("DaystoDays",1);
            ConversionsModel DaystoWeeks = new ConversionsModel("DaystoWeeks",0.142857);

            ConversionsModel WeekstoSeconds = new ConversionsModel("WeekstoSeconds",604800);
            ConversionsModel WeekstoMinutes = new ConversionsModel("WeekstoMinutes",10080);
            ConversionsModel WeekstoHours = new ConversionsModel("WeekstoHours",168);
            ConversionsModel WeekstoDays = new ConversionsModel("WeekstoDays",7);
            ConversionsModel WeekstoWeeks = new ConversionsModel("WeekstoWeeks",1);

            //VOLUME CONVERSION MODELS
            ConversionsModel MLtoML = new ConversionsModel("MLtoML", 1);
            ConversionsModel MLtoLitre = new ConversionsModel("MLtoLitre", 0.001);
            ConversionsModel MLtoPints = new ConversionsModel("MLtoPints", 0.00175975);
            ConversionsModel MLtoGallon = new ConversionsModel("MLtoGallon", 0.000219969);

            ConversionsModel LitretoML = new ConversionsModel("LitretoML", 1000);
            ConversionsModel LitretoLitre = new ConversionsModel("LitretoLitre", 1);
            ConversionsModel LitretoPints = new ConversionsModel("LitretoPints", 1.75975);
            ConversionsModel LitretoGallon = new ConversionsModel("LitretoGallon", 0.219969);

            ConversionsModel PintstoML = new ConversionsModel("PintstoML", 568.261);
            ConversionsModel PintstoLitre = new ConversionsModel("PintstoLitre", 0.568261);
            ConversionsModel PintstoPints = new ConversionsModel("PintstoPints", 1);
            ConversionsModel PintstoGallon = new ConversionsModel("PintstoGallon", 0.125);

            ConversionsModel GallontoML = new ConversionsModel("GallontoML", 4546.09);
            ConversionsModel GallontoLitre = new ConversionsModel("GallontoLitre", 4.54609);
            ConversionsModel GallontoPints = new ConversionsModel("GallontoPints", 8);
            ConversionsModel GallontoGallon = new ConversionsModel("GallontoGallon", 1);

            //TEMPERATURE CONVERSION MODELS
            ConversionsModel KelvintoKelvin = new ConversionsModel("KelvintoKelvin", 1);
            ConversionsModel KelvintoCelsius = new ConversionsModel("KelvintoCelsius", -272.15);
            ConversionsModel KelvintoFahrenheit = new ConversionsModel("KelvintoFahrenheit", -457.87);

            ConversionsModel CelsiustoKelvin = new ConversionsModel("CelsiustoKelvin", 2.23694);
            ConversionsModel CelsiustoCelsius = new ConversionsModel("CelsiustoCelsius", 1.8);
            ConversionsModel CelsiustoFahrenheit = new ConversionsModel("CelsiustoFahrenheit", 1);

            ConversionsModel FahrenheittoKelvin = new ConversionsModel("FahrenheittoKelvin", 2.23694);
            ConversionsModel FahrenheittoCelsius = new ConversionsModel("FahrenheittoCelsius", 3.6);
            ConversionsModel FahrenheittoFahrenheit = new ConversionsModel("FahrenheittoFahrenheit", 1);

            //STORAGE CONVERSION MODELS
            ConversionsModel KBtoKB = new ConversionsModel("KBtoKB", 1);
            ConversionsModel KBtoMB = new ConversionsModel("KBtoMB", 0.001);
            ConversionsModel KBtoGB = new ConversionsModel("KBtoGB", 1e-6);
            ConversionsModel KBtoTB = new ConversionsModel("KBtoTB", 1e-9);

            ConversionsModel MBtoKB = new ConversionsModel("MBtoKB", 1000);
            ConversionsModel MBtoMB = new ConversionsModel("MBtoMB", 1);
            ConversionsModel MBtoGB = new ConversionsModel("MBtoGB", 0.001);
            ConversionsModel MBtoTB = new ConversionsModel("MBtoTB", 1e+6);

            ConversionsModel GBtoKB = new ConversionsModel("GBtoKB", 1e+6);
            ConversionsModel GBtoMB = new ConversionsModel("GBtoMB", 1000);
            ConversionsModel GBtoGB = new ConversionsModel("GBtoGB", 1);
            ConversionsModel GBtoTB = new ConversionsModel("GBtoTB", 0.001);

            ConversionsModel TBtoKB = new ConversionsModel("TBtoKB", 1e+9);
            ConversionsModel TBtoMB = new ConversionsModel("TBtoMB", 1e+6);
            ConversionsModel TBtoGB = new ConversionsModel("TBtoGB", 1000);
            ConversionsModel TBtoTB = new ConversionsModel("TBtoTB", 1);


            //Add Weight into the hashmap
            convList.put(KGtoKG.getClassifier(), KGtoKG);
            convList.put(KGtoPounds.getClassifier(), KGtoPounds);
            convList.put(KGtoStone.getClassifier(), KGtoStone);
            convList.put(KGtoOunce.getClassifier(), KGtoOunce);
            convList.put(KGtoTonne.getClassifier(), KGtoTonne);
            convList.put(KGtoGram.getClassifier(), KGtoGram);

            convList.put(LBStoKG.getClassifier(), LBStoKG);
            convList.put(LBStoLBS.getClassifier(), LBStoLBS);
            convList.put(LBStoStone.getClassifier(), LBStoStone);
            convList.put(LBStoOunce.getClassifier(), LBStoOunce);
            convList.put(LBStoTonne.getClassifier(), LBStoTonne);
            convList.put(LBStoGram.getClassifier(), LBStoGram);

            convList.put(StonetoKG.getClassifier(), StonetoKG);
            convList.put(StonetoLBS.getClassifier(), StonetoLBS);
            convList.put(StonetoStone.getClassifier(), StonetoStone);
            convList.put(StonetoOunce.getClassifier(), StonetoOunce);
            convList.put(StonetoTonne.getClassifier(), StonetoTonne);
            convList.put(StonetoGram.getClassifier(), StonetoGram);

            convList.put(OuncetoKG.getClassifier(), OuncetoKG);
            convList.put(OuncetoLBS.getClassifier(), OuncetoLBS);
            convList.put(OuncetoStone.getClassifier(), OuncetoStone);
            convList.put(OuncetoOunce.getClassifier(), OuncetoOunce);
            convList.put(OuncetoTonne.getClassifier(), OuncetoTonne);
            convList.put(OuncetoGram.getClassifier(), OuncetoGram);

            convList.put(TonnetoKG.getClassifier(), TonnetoKG);
            convList.put(TonnetoLBS.getClassifier(), TonnetoLBS);
            convList.put(TonnetoStone.getClassifier(), TonnetoStone);
            convList.put(TonnetoOunce.getClassifier(), TonnetoOunce);
            convList.put(TonnetoTonne.getClassifier(), TonnetoTonne);
            convList.put(TonnetoGram.getClassifier(), TonnetoGram);

            convList.put(GramtoKG.getClassifier(), GramtoKG);
            convList.put(GramtoLBS.getClassifier(), GramtoLBS);
            convList.put(GramtoStone.getClassifier(), GramtoStone);
            convList.put(GramtoOunce.getClassifier(), GramtoOunce);
            convList.put(GramtoTonne.getClassifier(), GramtoTonne);
            convList.put(GramtoGram.getClassifier(), GramtoGram);

            //Add Speed to the hashmap
            convList.put(MPHtoMPH.getClassifier(), MPHtoMPH);
            convList.put(MPHtoKPH.getClassifier(), MPHtoKPH);
            convList.put(MPHtoMS.getClassifier(), MPHtoMS);
            convList.put(MPHtoKnots.getClassifier(), MPHtoKnots);

            convList.put(KPHtoMPH.getClassifier(), KPHtoMPH);
            convList.put(KPHtoKPH.getClassifier(), KPHtoKPH);
            convList.put(KPHtoMS.getClassifier(), KPHtoMS);
            convList.put(KPHtoKnots.getClassifier(), KPHtoKnots);

            convList.put(MStoMPH.getClassifier(), MStoMPH);
            convList.put(MStoKPH.getClassifier(), MStoKPH);
            convList.put(MStoMS.getClassifier(), MStoMS);
            convList.put(MStoKnots.getClassifier(), MStoKnots);

            convList.put(KnotstoMPH.getClassifier(), KnotstoMPH);
            convList.put(KnotstoKPH.getClassifier(), KnotstoKPH);
            convList.put(KnotstoMS.getClassifier(), KnotstoMS);
            convList.put(KnotstoKnots.getClassifier(), KnotstoKnots);

            //Add Distance to hashmap
            convList.put(CMtoCM.getClassifier(), CMtoCM);
            convList.put(CMtoMM.getClassifier(), CMtoMM);
            convList.put(CMtoFeet.getClassifier(), CMtoFeet);
            convList.put(CMtoMiles.getClassifier(), CMtoMiles);
            convList.put(CMtoKM.getClassifier(), CMtoKM);

            convList.put(MMtoCM.getClassifier(), MMtoCM);
            convList.put(MMtoMM.getClassifier(), MMtoMM);
            convList.put(MMtoFeet.getClassifier(), MMtoFeet);
            convList.put(MMtoMiles.getClassifier(), MMtoMiles);
            convList.put(MMtoKM.getClassifier(), MMtoKM);

            convList.put(FeettoCM.getClassifier(), FeettoCM);
            convList.put(FeettoMM.getClassifier(), FeettoMM);
            convList.put(FeettoFeet.getClassifier(), FeettoFeet);
            convList.put(FeettoMiles.getClassifier(), FeettoMiles);
            convList.put(FeettoKM.getClassifier(), FeettoKM);

            convList.put(MilestoCM.getClassifier(), MilestoCM);
            convList.put(MilestoMM.getClassifier(), MilestoMM);
            convList.put(MilestoFeet.getClassifier(), MilestoFeet);
            convList.put(MilestoMiles.getClassifier(), MilestoMiles);
            convList.put(MilestoKM.getClassifier(), MilestoKM);

            convList.put(KMtoCM.getClassifier(), KMtoCM);
            convList.put(KMtoMM.getClassifier(), KMtoMM);
            convList.put(KMtoFeet.getClassifier(), KMtoFeet);
            convList.put(KMtoMiles.getClassifier(), KMtoMiles);
            convList.put(KMtoKM.getClassifier(), KMtoKM);

            //Add Time to Hashmap
            convList.put(SecondstoSeconds.getClassifier(), SecondstoSeconds);
            convList.put(SecondstoMinutes.getClassifier(), SecondstoMinutes);
            convList.put(SecondstoHours.getClassifier(), SecondstoHours);
            convList.put(SecondstoDays.getClassifier(), SecondstoDays);
            convList.put(SecondstoWeeks.getClassifier(), SecondstoWeeks);

            convList.put(MinutestoSeconds.getClassifier(), MinutestoSeconds);
            convList.put(MinutestoMinutes.getClassifier(), MinutestoMinutes);
            convList.put(MinutestoHours.getClassifier(), MinutestoHours);
            convList.put(MinutestoDays.getClassifier(), MinutestoDays);
            convList.put(MinutestoWeeks.getClassifier(), MinutestoWeeks);

            convList.put(HourstoSeconds.getClassifier(), HourstoSeconds);
            convList.put(HourstoMinutes.getClassifier(), HourstoMinutes);
            convList.put(HourstoHours.getClassifier(), HourstoHours);
            convList.put(HourstoDays.getClassifier(), HourstoDays);
            convList.put(HourstoWeeks.getClassifier(), HourstoWeeks);

            convList.put(DaystoSeconds.getClassifier(), DaystoSeconds);
            convList.put(DaystoMinutes.getClassifier(), DaystoMinutes);
            convList.put(DaystoHours.getClassifier(), DaystoHours);
            convList.put(DaystoDays.getClassifier(), DaystoDays);
            convList.put(DaystoWeeks.getClassifier(), DaystoWeeks);

            convList.put(WeekstoSeconds.getClassifier(), WeekstoSeconds);
            convList.put(WeekstoMinutes.getClassifier(), WeekstoMinutes);
            convList.put(WeekstoHours.getClassifier(), WeekstoHours);
            convList.put(WeekstoDays.getClassifier(), WeekstoDays);
            convList.put(WeekstoWeeks.getClassifier(), WeekstoWeeks);

            //Add Volume to Hashmap
            convList.put(MLtoML.getClassifier(), MLtoML);
            convList.put(MLtoLitre.getClassifier(), MLtoLitre);
            convList.put(MLtoPints.getClassifier(), MLtoPints);
            convList.put(MLtoGallon.getClassifier(), MLtoGallon);

            convList.put(LitretoML.getClassifier(), LitretoML);
            convList.put(LitretoLitre.getClassifier(), LitretoLitre);
            convList.put(LitretoPints.getClassifier(), LitretoPints);
            convList.put(LitretoGallon.getClassifier(), LitretoGallon);

            convList.put(PintstoML.getClassifier(), PintstoML);
            convList.put(PintstoLitre.getClassifier(), PintstoLitre);
            convList.put(PintstoPints.getClassifier(), PintstoPints);
            convList.put(PintstoGallon.getClassifier(), PintstoGallon);

            convList.put(GallontoML.getClassifier(), GallontoML);
            convList.put(GallontoLitre.getClassifier(), GallontoLitre);
            convList.put(GallontoPints.getClassifier(), GallontoPints);
            convList.put(GallontoGallon.getClassifier(), GallontoGallon);

            //Add Temperature to the Hashmap
            convList.put(KelvintoKelvin.getClassifier(), KelvintoKelvin);
            convList.put(KelvintoCelsius.getClassifier(), KelvintoCelsius);
            convList.put(KelvintoFahrenheit.getClassifier(), KelvintoFahrenheit);

            convList.put(CelsiustoKelvin.getClassifier(), CelsiustoKelvin);
            convList.put(CelsiustoCelsius.getClassifier(), CelsiustoCelsius);
            convList.put(CelsiustoFahrenheit.getClassifier(), CelsiustoFahrenheit);

            convList.put(FahrenheittoKelvin.getClassifier(), CelsiustoKelvin);
            convList.put(FahrenheittoCelsius.getClassifier(), CelsiustoCelsius);
            convList.put(FahrenheittoFahrenheit.getClassifier(), CelsiustoFahrenheit);

            //ADD STORAGE TO THE HASHMAP
            convList.put(KBtoKB.getClassifier(), KBtoKB);
            convList.put(KBtoMB.getClassifier(), KBtoMB);
            convList.put(KBtoGB.getClassifier(), KBtoGB);
            convList.put(KBtoTB.getClassifier(), KBtoTB);

            convList.put(MBtoKB.getClassifier(), MBtoKB);
            convList.put(MBtoMB.getClassifier(), MBtoMB);
            convList.put(MBtoGB.getClassifier(), MBtoGB);
            convList.put(MBtoTB.getClassifier(), MBtoTB);

            convList.put(GBtoKB.getClassifier(), GBtoKB);
            convList.put(GBtoMB.getClassifier(), GBtoMB);
            convList.put(GBtoGB.getClassifier(), GBtoGB);
            convList.put(GBtoTB.getClassifier(), GBtoTB);

            convList.put(TBtoKB.getClassifier(), TBtoKB);
            convList.put(TBtoMB.getClassifier(), TBtoMB);
            convList.put(TBtoGB.getClassifier(), TBtoGB);
            convList.put(TBtoTB.getClassifier(), TBtoTB);


            //For Each loop to and all models into database
            for (Map.Entry<String, ConversionsModel> entry : convList.entrySet())
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
