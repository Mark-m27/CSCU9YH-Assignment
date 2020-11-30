package com.example.unitconversionapp;

public class ConversionsModel
{
    //Model that will be stored in database
    private int id;
    private String classifier;
    private double multiplier;

    //Contructor for Database
    public ConversionsModel(int id, String classifier, double multiplier)
    {
        this.id = id;
        this.classifier = classifier;
        this.multiplier = multiplier;
    }

    public ConversionsModel(String classifier, double multiplier) {
        this.classifier = classifier;
        this.multiplier = multiplier;
    }

    //Non param Constructor
    public ConversionsModel() { }

    //toString method for debugging


    @Override
    public String toString() {
        return classifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
