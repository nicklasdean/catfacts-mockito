package com.example.demo.services;

import com.example.demo.models.CatFact;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class CatFactService {
    URL catURL;
    BufferedReader inputFromCatUrl;

    public CatFactService() throws IOException {
         this.catURL = new URL("http://cat-fact.herokuapp.com/facts/random");
    }
    //Fetching a single cat fact
    public CatFact getSingleCatFact() throws IOException {
        this. inputFromCatUrl = new BufferedReader(new InputStreamReader(this.catURL.openStream()));
        CatFact singleCatFact = new Gson().fromJson(inputFromCatUrl,CatFact.class);
        inputFromCatUrl.close();
        return singleCatFact;
    }
    //Calling the method 10 times
    public ArrayList<CatFact> getTen() throws IOException {
        ArrayList<CatFact> tenCatFacts = new ArrayList<CatFact>();
        for(int i = 0 ; i < 10 ; i++){
            tenCatFacts.add(getSingleCatFact());
        }
        return tenCatFacts;
    }
    //Sorting collection of cat facts
    public ArrayList<CatFact> getTenSortedByDate() throws IOException {
        ArrayList<CatFact> catFactsToReturn = getTen();
        Collections.sort(catFactsToReturn);
        return catFactsToReturn;
    }
    //Solving an algorithm - only return if contain n times
    public String getCatIfContainsLetter(char c, int n) throws IOException {
        String catFactText = getSingleCatFact().getText();
        int count = catFactText.length() - catFactText.replaceAll(String.valueOf(c),"").length();
        if(count > n){
            return catFactText;
        }
        else return "Too bad";
    }
}
