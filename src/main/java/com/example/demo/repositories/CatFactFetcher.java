package com.example.demo.repositories;

import com.example.demo.models.CatFact;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class CatFactFetcher {
    URL catURL;
    BufferedReader inputFromCatUrl;

    public CatFact fetchSingleCatFact() throws IOException {
        this.catURL = new URL("http://cat-fact.herokuapp.com/facts/random");
        this.inputFromCatUrl = new BufferedReader(new InputStreamReader(this.catURL.openStream()));
        CatFact catFact = new Gson().fromJson(inputFromCatUrl,CatFact.class);
        inputFromCatUrl.close();
        return catFact;
    }
}
