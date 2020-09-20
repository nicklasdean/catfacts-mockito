package com.example.demo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.demo.models.CatFact;
import com.example.demo.repositories.CatFactFetcher;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class CatFactServiceTest {
    CatFactFetcher mockedFetcher;   //Mocked fetcher-object that will return local data
    CatFactService service;         //Service we want to test


    @BeforeEach
    public void instantiateWithMockObject(){
        //Instantiating the fetcher with the mocked fetcher object
        mockedFetcher = Mockito.mock(CatFactFetcher.class);

        //Instantiating the service with the mocked fetcher object
        service = new CatFactService(mockedFetcher);
    }


    @Test
    public void getSingleCatFact() throws IOException {
        //When the mocked fetcher object is called, we are returning test data defined in test.resources.catfact.json
        Mockito.when(mockedFetcher.fetchSingleCatFact()).thenReturn(fetchSingleLocalCatFact());

        //Making assertion(s) - one for brevity
        assertEquals("The cat's footpads absorb the shocks of the landing when the cat jumps." , service.getSingleCatFact().getText());
    }

    @Test
    public void getTen() throws IOException {
        //Verify that the getSingleCatFact() method calls the fetcher 10 times exactly by the service .getTen() Method
        service.getTen();
        verify(mockedFetcher, times(10)).fetchSingleCatFact();
    }

    @Test
    public void getTenWithSpy() throws IOException {
        //Mocked service we want to test - using a spy.
        CatFactService mockService;

        //Instantiating the mock service with the mocked fetcher object
        mockService = Mockito.mock(CatFactService.class);

        //Verify that the getSingleCatFact() method calls the fetcher 10 times exactly by the service .getTen() Method
        mockService.getTen();

        //Spying on the service and verifying the method is being called exactly once
        verify(mockService, times(1)).getTen();
    }

    private CatFact fetchSingleLocalCatFact() throws FileNotFoundException {
        //Reading a local json test-file (instead of fetching)
        JsonReader catfactReader = new JsonReader(new FileReader(new File("src/test/resources/catfact.json")));
        return new Gson().fromJson(catfactReader,CatFact.class);
    }
}