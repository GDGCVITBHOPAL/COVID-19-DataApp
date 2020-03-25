package com.test.coronaapp;

public class Country {
    String Ccountry, Ccases;
    public Country(String Ccountry, String Ccases) {
        this.Ccountry = Ccountry;
        this.Ccases = Ccases;

    }

    public String getCcountry()
    {
        return Ccountry;
    }
    public String getCcases()
    {
        return Ccases;
    }
}
